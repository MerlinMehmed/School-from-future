INSERT INTO Role (name, description) VALUES ('admin', 'Web site admin');
INSERT INTO Role (name, description) VALUES ('teacher', 'School teacher');
INSERT INTO Role (name, description) VALUES ('student', 'School student');

-- more rights to be added
-- INSERT INTO Right (name, description) VALUES ('register user', 'Register a new user');

-- more connections to be added 
-- INSERT INTO role_right (role_name, right_name) VALUES ('admin', 'register user');

INSERT INTO User (first_name, last_name, email, pass, role) VALUES ('Admin', 'Adminov', 'admin@gmail.com', 'admincho', 1);

INSERT INTO Admin (user_id) VALUES (1);