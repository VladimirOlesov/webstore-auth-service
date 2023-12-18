INSERT INTO users (user_uuid, username, password, email, phone_number, registration_date,
                   first_name, last_name, role)

VALUES ('3dad110d-4ba0-4f35-aa87-65d24a185301', 'Leva12',
        '$2a$12$EPkeblBi8JjzDzcqSZDKyOpdlQRdCojL92K7g1PqA9EPVLhDUUsoK',
        'example@example.com', '+1234567890', current_timestamp, 'Lev', 'Doe', 'USER'),

       (uuid_generate_v4(), 'Ivan34',
        '$2a$12$EPkeblBi8JjzDzcqSZDKyOpdlQRdCojL92K7g1PqA9EPVLhDUUsoK',
        'example@example2.com', '+0987654321', current_timestamp, 'Ivan', 'Stew', 'ADMIN');