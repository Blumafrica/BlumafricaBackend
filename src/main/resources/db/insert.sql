use bluma_backend;

# create table post
# (
#     id                 int          not null,
#     postOwnerId        int          not null,
#     content            varchar(255) not null,
#     createdAt          varchar(255) not null,
#     description        varchar(255) not null,
#     fileUrl            varchar(255) not null,
#     postOwnerAuthority varchar(255) not null,
#     totalOfShare        int,
#     totalOfComment      int,
#     totalOfLike         int
#
# );
insert into post (id, post_owner_id, content, created_at, description, file_url, post_owner_authority, total_of_comment, total_of_like, total_of_share)
value (101, 103, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 2, 2,2),
      (102, 105, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 2,3 ,3),
    (103, 106, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 3,2, 1),
    (104, 107, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 1, 1, 4),
    (105, 108, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN', 4, 5, 2),
    (106, 109, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,1,1),
    (111, 129, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,3,4),
    (100, 128, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',4,6,6),
    (109, 127, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',4,6,2),
    (108, 126, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',4,2,2),
    (121, 125, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',3,1,3),
    (107, 124, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',3,1,3),
    (126, 123, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,1,3),
    (145, 122, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',1,2,3),
    (122, 121, 'testing', '2023-12-03T08:52:33.075+01:00', 'testing 123', 'www.getFilePath.com', 'ADMIN',3,2,2);

