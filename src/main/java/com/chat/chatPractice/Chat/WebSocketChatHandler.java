package com.chat.chatPractice.Chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;

    //현재 연결된 세션들
    private final Set<WebSocketSession> sessions = new HashSet<>();

    // 채팅방 아이디가 String 키값이고, set은 {session1, session2}
    private final Map<String,Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();



    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        log.info("{} 연결됨",session.getId());
        sessions.add(session);
    }

    // 소켓 통신 시 메시지 전송을 handle
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
            String payload = message.getPayload();


            // 페이로드 -> chatMessageDto로 변환
            ChatDTO chatDTO = mapper.readValue(payload, ChatDTO.class);
            chatDTO.setSenderId(session.getPrincipal().getName());
            log.info("payload {}", payload);
            log.info("session {}", chatDTO.toString());

            String chatRoomId = chatDTO.getChatRoomId();
            // 메모리 상에 채팅방에 대한 세션 없으면 만들어줌
            if (!chatRoomSessionMap.containsKey(chatRoomId))
            {
                chatRoomSessionMap.put(chatRoomId, new HashSet<>());
                log.info("created_chatRoomId {}", chatRoomId);
            }

            // chatRoomSession : 채팅방에 포함된 세션들의 집합이다.
            Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

            // message 에 담긴 타입을 확인한다.
            // 이때 message 에서 getType 으로 가져온 내용이
            // ChatDTO 의 열거형인 MessageType 안에 있는 ENTER 과 동일한 값이라면
            if (chatDTO.getMessageType().equals(ChatDTO.MessageType.ENTER))
            {
                // sessions 에 새로 들어온 session 을 담고,
                chatRoomSession.add(session);
            }

            //채팅방의 세션이 3개 이상이라면, 채팅방의 세션 집합에서, 전체세션 집합에 없는 것을 제거한다.
            if (chatRoomSession.size() >= 3)
            {
                removeClosedSession(chatRoomSession);
            }
            sendMessageToChatRoom(chatDTO, chatRoomSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
    {
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
    }

    public void removeClosedSession(Set<WebSocketSession> chatRoomSession)
    {
        chatRoomSession.removeIf(sess -> !sessions.contains(sess));
    }

    private void sendMessageToChatRoom(ChatDTO chatDTO, Set<WebSocketSession> chatRoomSession)
    {
        chatRoomSession.parallelStream()
                .filter(sess -> !sess.getId().equals(chatDTO.getSenderId()))  // 본인(senderId)에게는 전송하지 않음
                .forEach(sess -> sendMessage(sess, chatDTO));
    }

    //dto인 message를 다시 json인 string으로 변환하서 세션 클라이언트로 전송!!
    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            if(session.isOpen()) //닫힌 세션에 전송해서 에러뜬듯???????????????????????????????????!!!!!!!!!!
            {
                session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
            }
            else {
                log.info("이 세션은 닫혀서 전송안했음");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
