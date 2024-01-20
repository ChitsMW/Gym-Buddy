BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0,'Seated Leg Press','Strength training', null, null, null, 'Legs' );

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0,'Abdominal Crunch Machine','Strength training',null, null, null, 'Abdominal');

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0,'Leg Extension','Strength training' , null, null, null, 'Legs');

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0,'Chin Dip Assist','Strength training', null, null, null, 'Chest,Triceps');

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0,'Abductor','Strength training', null, null, null, 'Thighs');

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0,'Chest Press','Strength training',null, null, null, 'Upper body');

INSERT INTO equipment (total_reps, equipment_name,equipment_type,equipment_photo, instructions_video,instructions_animation,targeted_area)
VALUES (0, 'Functional Trainer', 'Strength training', null, null, null, 'Upper body and Lower body');


INSERT INTO gym_session (user_id, date, duration)
VALUES (1,'2024-01-03', 110);

INSERT INTO gym_session (user_id, date, duration)
VALUES (1,'2024-01-04', 127);

INSERT INTO gym_session (user_id, date, duration)
VALUES (1,'2024-01-07', 200);

INSERT INTO gym_session (user_id, date, duration)
VALUES (2,'2024-01-03', 150);

INSERT INTO gym_session (user_id, date, duration)
VALUES (2,'2024-01-07', 140);

INSERT INTO gym_session (user_id, date, duration)
VALUES (1,'2024-01-12', 70);

INSERT INTO gym_session (user_id, date, duration)
VALUES (1,'2024-01-13', 100);

INSERT INTO gym_session (user_id, date, duration)
VALUES (2,'2024-01-14', 120);

INSERT INTO gym_session (user_id, date, duration)
VALUES (2,'2024-01-18', 130);


INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (2,1,5,100);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (2,3,10,60);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (3,3,10,30);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (3,6,10,70);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (4,5,15,40);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (5,7,10,20);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (5,6,15,70);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (8,6,10,55);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (8,7,15,70);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (9,5,10,45);

INSERT INTO exercise_log (session_id, equipment_id, reps, weight)
VALUES (9,6,10,65);

COMMIT TRANSACTION;
