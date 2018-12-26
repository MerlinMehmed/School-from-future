INSERT INTO Role (name, description) VALUES ('admin', 'Web site admin');
INSERT INTO Role (name, description) VALUES ('teacher', 'School teacher');
INSERT INTO Role (name, description) VALUES ('student', 'School student');

-- more rights to be added
INSERT INTO User_Right (name, description) VALUES ('register user', 'Register a new user');

-- more connections to be added 
INSERT INTO role_right (role_name, right_name) VALUES ('admin', 'register user');

INSERT INTO User (first_name, last_name, email, pass, role) 
	VALUES ('Admin', 'Adminov', 'admin@gmail.com', 'admincho', 'admin');

INSERT INTO Admin (email) VALUES ('admin@gmail.com');

INSERT INTO User (first_name, last_name, email, pass, role) 
	VALUES ('Вяра', 'Младенова', 'vera@gmail.com', 'vera', 'teacher');

INSERT INTO Teacher (email) VALUES ('vera@gmail.com');

INSERT INTO User (first_name, last_name, email, pass, role) 
	VALUES ('Наско', 'Топалов', 'nasko@gmail.com', 'nasko', 'teacher');

INSERT INTO Teacher (email) VALUES ('nasko@gmail.com');

INSERT INTO User (first_name, last_name, email, pass, role) 
	VALUES ('Мира', 'Андреева', 'mira@gmail.com', 'mira', 'student');

INSERT INTO Student (email, class, class_number) VALUES ('mira@gmail.com', 10, 13);

INSERT INTO Subject (name, teacher_id, description) VALUES ('Математика', 'vera@gmail.com', 'Математика за 10 клас');

INSERT INTO Student_Subject (student_id, subject_id) VALUES ('mira@gmail.com', 1);
