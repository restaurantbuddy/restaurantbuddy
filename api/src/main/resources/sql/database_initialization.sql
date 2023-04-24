USE restaurantbuddy;

INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION)
VALUES (1, 'OWNER',
        'This login role is associated with the owner of the restaurant. This login role is able to add and remove employees at will as well as add and remove menu items.');

INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION)
VALUES (2, 'EMPLOYEE',
        'This login role is associated with the employees who work for the restaurant. They are able to view orders that are placed by customers as well as complete them.');

INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION)
VALUES (3, 'CUSTOMER',
        'This login role is associated with the customers of the restaurant. They will be allowed to add items to their shopping cart as well as place orders.');
