CREATE TABLE users (
                      id BIGSERIAL PRIMARY KEY,
                      login TEXT,
                      password TEXT);

CREATE INDEX task_login_idx ON users (login);