-- Insert initial taskstatus data
INSERT INTO taskstatus (name) VALUES ('Not Started');
INSERT INTO taskstatus (name) VALUES ('In Progress');
INSERT INTO taskstatus (name) VALUES ('Completed');

-- Insert initial user data
INSERT INTO users (username, password) VALUES ('admin', 'password123');
INSERT INTO users (username, password) VALUES ('test-user-1', 'password123');

-- Insert initial task data
INSERT INTO tasks (description, created_at, updated_at, user_id, status_id) 
VALUES ('Complete project report', '2025-03-23 10:00:00', '2025-03-23 10:00:00', 1, 1);

INSERT INTO tasks (description, created_at, updated_at, user_id, status_id) 
VALUES ('Review code changes', '2025-03-23 10:05:00', '2025-03-23 10:05:00', 2, 2);
