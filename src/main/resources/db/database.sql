
use bluma_backend;

insert into bluma_backend.like(id, is_liked, post_id, userathority, user_id)
values (201, true, 1, USER, 201),
       (202, true, 1, USER, 202),
       (203, true, 1, USER, 203),
       (204, true, USER, 204),
       (205, true, USER, 205),
       (206, true, USER, 206),
       (207, true, USER, 207),
       (208, true, USER, 208),
       (209, true, USER, 209);