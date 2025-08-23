package com.zherikhov.planningpoker.api.rooms;

/**
 * Response returned after successful room creation.
 */
public record CreateRoomResponse(String id, String code) {}

