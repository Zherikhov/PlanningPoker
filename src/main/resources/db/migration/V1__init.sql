-- Initial schema for Planning Poker application
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS rooms (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id VARCHAR(50) PRIMARY KEY,
    room_id VARCHAR(50) REFERENCES rooms(id),
    summary VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS votes (
    id SERIAL PRIMARY KEY,
    task_id VARCHAR(50) REFERENCES tasks(id),
    user_id VARCHAR(50) REFERENCES users(id),
    value INT NOT NULL
);
