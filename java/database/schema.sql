BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, fitness_goals, user_profile, gym_session, classes, class_user, equipment, exercise_log;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE fitness_goals (
    fitness_goals_id SERIAL PRIMARY KEY,
    weight_loss BOOLEAN,
    muscle_gain BOOLEAN,
    endurance BOOLEAN,
    consistency BOOLEAN,
    flexibility BOOLEAN,
    other VARCHAR(255)
);
CREATE TABLE user_profile (
    profile_id SERIAL PRIMARY KEY,
    user_id INT,
    name VARCHAR(255),
    email VARCHAR(255),
    photo_url VARCHAR(255),
    age INT,
    height FLOAT,
    weight FLOAT,
    experience_level VARCHAR(255),
    fitness_goals_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (fitness_goals_id) REFERENCES fitness_goals(fitness_goals_id)
);
CREATE TABLE gym_session (
    session_id SERIAL PRIMARY KEY,
    user_id INT,
    date DATE,
    duration INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE classes (
    class_id SERIAL PRIMARY KEY,
    class_name VARCHAR(255),
    date_time TIMESTAMP,
    instructor VARCHAR(255),
    spots_available INT
);
CREATE TABLE class_user (
	class_id INT,
	user_id INT,
	PRIMARY KEY (class_id, user_id),
    FOREIGN KEY (class_id) REFERENCES classes(class_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE equipment (
    equipment_id SERIAL PRIMARY KEY,
    total_reps INT,
    equipment_name VARCHAR(255),
    equipment_type VARCHAR(255),
    equipment_photo VARCHAR(255),
    instructions_video VARCHAR(255),
    instructions_animation VARCHAR(255),
    targeted_area VARCHAR(255)
);
CREATE TABLE exercise_log (
    log_id SERIAL PRIMARY KEY,
    session_id INT,
    equipment_id INT,
    reps INT,
    weight FLOAT,
    FOREIGN KEY (session_id) REFERENCES gym_session(session_id),
    FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id)
);

COMMIT TRANSACTION;