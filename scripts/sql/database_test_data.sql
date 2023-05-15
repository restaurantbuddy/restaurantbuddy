USE restaurantbuddy;

-- This is a SQL script to populate the RestaurantBuddy database with test data. It is supposed to run right after the
-- first user is created in the system (as this user will be granted administrator privileges). After this, the script
-- will automatically populate the database with dummy menu items and locations.

-- Script to grant the first user you create administrator privileges.
INSERT INTO OWNER
VALUES (1, 1);

INSERT INTO EMPLOYEE
VALUES (1, 0.00, 1);

-- Dummy Menu Items
INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_COST)
VALUES (1, 'Peking Roasted Duck', 'Tortor at risus viverra adipiscing at in tellus integer feugiat.', 20.99);

INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_COST)
VALUES (2, 'Kung Pao Chicken', 'Amet massa vitae tortor condimentum lacinia.', 12.99);

INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_COST)
VALUES (3, 'Sweet and Sour Pork', 'Nunc sed blandit libero volutpat sed cras ornare arcu.', 7.99);

INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_COST)
VALUES (4, 'Hot Pot', 'Nunc sed blandit libero volutpat sed cras ornare arcu.', 7.99);

INSERT INTO ITEM (ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_COST)
VALUES (5, 'Dumplings', 'Non consectetur a erat nam at lectus urna duis.', 6.99);

-- Dummy Locations
INSERT INTO LOCATION (LOCATION_ID, LOCATION_NAME, LOCATION_ADDRESS, LOCATION_CITY, LOCATION_STATE, LOCATION_ZIP)
VALUES (1, 'Sunny''s Noodle House', '2331 Velp Ave', 'Green Bay', 'WI', '54303');

INSERT INTO LOCATION (LOCATION_ID, LOCATION_NAME, LOCATION_ADDRESS, LOCATION_CITY, LOCATION_STATE, LOCATION_ZIP)
VALUES (2, 'Green Tea Chinese Drive Thru', '711 N Memorial Dr', 'Green Bay', 'WI', '54303');

INSERT INTO LOCATION (LOCATION_ID, LOCATION_NAME, LOCATION_ADDRESS, LOCATION_CITY, LOCATION_STATE, LOCATION_ZIP)
VALUES (3, 'Mayflower Restaurant', '414 Military Ave', 'Green Bay', 'WI', '54303');

INSERT INTO LOCATION (LOCATION_ID, LOCATION_NAME, LOCATION_ADDRESS, LOCATION_CITY, LOCATION_STATE, LOCATION_ZIP)
VALUES (4, 'China Palace Restaurant', '213 N Washington St', 'Green Bay', 'WI', '54301');

INSERT INTO LOCATION (LOCATION_ID, LOCATION_NAME, LOCATION_ADDRESS, LOCATION_CITY, LOCATION_STATE, LOCATION_ZIP)
VALUES (5, 'Lin''s Garden', '107 Military Ave', 'Green Bay', 'WI', '54303');
