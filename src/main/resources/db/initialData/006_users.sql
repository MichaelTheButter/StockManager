insert into users(user_name, password, user_role)
# main / adminpass
# sampleuser / userpass
# viewer / viewerpass
values ('admin', '{bcrypt}$2a$12$abynjFuLDyfvxMVM/2cvSO2I1VgS2TqGRFm0ACmte4BOmvhTq.M.m', 'admin'),
       ('sampleuser', '{bcrypt}$2a$12$yPwrqqP5wua4eEc3EWb2quxR.Jc.WaxC2.YWXUehGb2fOdGa7MLmq', 'user'),
       ('viewer', '{bcrypt}$2a$12$UjkfzckdUbDC2GxjvgJFDOSO2feRmBX..vj0EM18MX.r5ZXvq2p52', 'viewer');
