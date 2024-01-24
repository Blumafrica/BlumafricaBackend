use bluma_backend;


insert into post( id, post_owner_id, content, created_at, description, file_url, post_owner_authority, total_of_comment, total_of_like, total_of_share)
value (101, 100, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 2, 2,2),
      (102, 101, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 2,3 ,3),
    (103, 100, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 3,2, 1),
    (104, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 1, 1, 4),
    (105, 102, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 4, 5, 2),
    (106, 102, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,1,1),
    (107, 106, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,3,4),
    (108, 100, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',4,6,6),
    (109, 105, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',4,6,2),
    (110, 105, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',4,2,2),
    (111, 106, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',3,1,3),
    (112, 104, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',3,1,3),
    (113, 104, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,1,3),
    (114, 105, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,2,3),
    (115, 100, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',3,2,2);




insert into post (id, post_owner_id, content, created_at, description, file_url, post_owner_authority)
    value (201, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
          (291, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(223, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(294, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(225, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(296, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(327, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(338, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(329, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER'),
(300, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'USER');


insert into profile(id, about, cover_picture, headline, profile_picture, phone_number, gender, firstname )
value (102, 'about', 'www.coverPhoto', 'headline', 'www.profilePictures', '0903388338','FEMALE', 'first');

insert into user (id, username, authorities, password, email, profile_id)
values (101, 'mariam','USER', 'password', 'mariam@gmail.com', 101);

insert into post(id, post_owner_id, content, created_at, description, file_url, post_owner_authority)
  value (102, 1, 'i love nigeria','2024-01-18T14:35:19.939+01:00', 'testing','C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg','ADMIN'  ),
    (203, 1, 'i love nigeria','2024-01-18T14:35:19.939+01:00', 'testing','C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg'  ,'ADMIN'),
    (302, 1, 'i love nigeria','2024-01-18T14:35:19.939+01:00', 'testing','C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg' ,'ADMIN' ),
    (402, 1, 'i love nigeria','2024-01-18T14:35:19.939+01:00', 'testing','C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg' , 'ADMIN' ),
    (502, 1, 'i love nigeria','2024-01-18T14:35:19.939+01:00', 'testing','C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg' , 'ADMIN' ),
    (602, 1, 'i love nigeria','2024-01-18T14:35:19.939+01:00', 'testing','C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg'  ,'ADMIN');

insert into profile(id,  about, cover_picture, firstname, headline, lastname, phone_number, profile_picture, gender)
     values (104, 'im a girl', 'C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg', 'mariam','headline', 'ambali', '09032489234', 'C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\the cat.jpeg', 'female'),
            (102, 'im a girl', 'C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\e field.jpeg', 'maryam','headline', 'ambali', '09052489234', 'C:\Users\mariam\Desktop\BlumafricaBackend\src\main\resources\assets\the cat.jpeg', 'female');


         insert into user(id, email, profile_id, username, password, authorities)
values(104, 'ambalimariam91@gmail.com', 104, 'firstUser', 'password', 'user'),
      (102, 'mariam91@gmail.com', 102, 'secondUser', 'password1', 'user');


use bluma_backend;


# insert into  post (id, post_owner_id, content, created_at, description, file_url, post_)
