package com.zherikhov.planningpoker.boards.api;

import com.zherikhov.planningpoker.boards.model.Room;

public interface CreateRoomUseCase {
    Room createRoom(String name);
}
