USE restaurantbuddy;

-- Dummy Logins
-- Role Key: OWNER = 1, EMPLOYEE = 2, CUSTOMER = 3

-- Username: sdfds
-- Password: Password1234
-- Hashing Algorithm: Blowfish
-- No Salt
INSERT INTO LOGIN (LOGIN_ID, LOGIN_USERNAME, LOGIN_PASSWORD_HASH)
VALUES (1, 'sdfds', '$2y$10$B6KE.O6du3CjS3JHJRUg/ukqSvB81neEYWgsUfhbh.nI/Y4l4sfWC');

-- Username: asdfjkl;
-- Password: Candy1234
-- Hashing Algorithm: Blowfish
-- No Salt
INSERT INTO LOGIN (LOGIN_ID, LOGIN_USERNAME, LOGIN_PASSWORD_HASH)
VALUES (2, 'asdfjkl;', '$2y$10$nRmIzxFUIvY0ePADj2KgHedK0L55vcYqk1bGWxbr1xyCFJRQbFkfa');

INSERT INTO USER (USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_PHONE, USER_ADDRESS, USER_CITY, USER_STATE,
                  USER_ZIP, LOGIN_ID)
VALUES (1, 'John', 'Doe', 'john.doe@example.com', '1234567890', '1234 Main St.', 'Green Bay', 'WI', '54303', 1);

INSERT INTO EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_SALARY, USER_ID)
VALUES (1, 5000.00, 1);


