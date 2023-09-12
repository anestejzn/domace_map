-- sifra1234A2@

insert into role (role_name) values ('ROLE_USER'),
                                    ('ROLE_ADMIN');

insert into privilege (privilege_name) values ('GET_HOUSEHOLD'),
                                              ('SAVE_HOUSEHOLD'),
                                              ('CREATE_OFFER'),
                                              ('GET_OFFERS'),
                                              ('UPDATE_OFFER'),
                                              ('DELETE_OFFER'),
                                              ('CREATE_ORDER'),
                                              ('GET_ORDERS'),
                                              ('GET_ORDER'),
                                              ('MARK_AS_DELIVERED'),
                                              ('MARK_AS_SENT'),
                                              ('SAVE_REVIEW'),
                                              ('REJECT_ORDER'),
                                              ('GET_REVIEWS'),
                                              ('GET_HOUSEHOLDS'),
                                              ('GET_HOUSEHOLD'),
                                              ('GET_REVIEWS');

insert into role_privilege (role_id, privilege_id) values (1, 1),
                                                          (1, 2),
                                                          (1, 3),
                                                          (1, 4),
                                                          (2, 4),
                                                          (1, 5),
                                                          (1, 6),
                                                          (2, 6),
                                                          (1, 7),
                                                          (1, 8),
                                                          (2, 8),
                                                          (1, 9),
                                                          (2, 9),
                                                          (1, 10),
                                                          (1, 11),
                                                          (1, 12),
                                                          (1, 13),
                                                          (1, 14),
                                                          (2, 14),
                                                          (2, 15),
                                                          (1, 16),
                                                          (2, 16),
                                                          (2, 17);

insert into address (street, number, city, lon, lat) values
                    ('Bozidara Milunovica', '7', 'Kraljevo', 20.69135, 43.72146);
insert into household (name, phone_number, registration_number, address_id) values
                      ('Samcovic', '06474738493', '12345678', 1);

insert into regular_user (id, email, password, name, surname, salt, failed_attempts, locked_until, verified, role_id,blocked, household_id) values
                        (nextval('users_id_gen'), 'domacemap+ana@gmail.com', '$2y$10$uwgoYpON2hx80Xpfgn4.O.j0Pys.uATCE2gQu3BNr/DwC8qn6G9am', 'Ana', 'Anic', '123', 0, null, true, 1, false, 1),
                        (nextval('users_id_gen'), 'domacemap+miki@gmail.com', '$2y$10$uwgoYpON2hx80Xpfgn4.O.j0Pys.uATCE2gQu3BNr/DwC8qn6G9am', 'Miki', 'Mikic', '123', 0, null, true, 1, false, null);
insert into admin (id, email, password, name, surname, salt, failed_attempts, locked_until, verified, role_id, blocked) values
    (nextval('users_id_gen'), 'domacemap+admin@gmail.com', '$2y$10$uwgoYpON2hx80Xpfgn4.O.j0Pys.uATCE2gQu3BNr/DwC8qn6G9am', 'Admin', 'Admin', '123', 0, null, true, 2, false);

insert into offer (name, description, price, col_for_price, average_rate, household_id, type) values
            ('Jabuka', 'Prelepe', 100, '1kg', 0, 1, 'Jabuka'),
            ('Malina domaća', 'Preukusne, krupne..', 300, '1kg', 0, 1, 'Malina'),
            ('Kupina', 'Prelepe', 400, '1kg', 0, 1, 'Kupina'),
            ('Višnje domaće', 'Prelepe', 150, '1kg', 0, 1, 'Višnja');

insert into offer_photo (offer_id, photo) values
            (1, 'offer-1-1.png'),
            (2, 'offer-2-0.png'),
            (3, 'offer-3-0.png'),
            (4, 'offer-4-0.png');
insert into product_order (cancel_reason, cancelled, date_time, delivered_at, phone_number, sent_at, address_id, household_id, user_id) values
                            ('', false, '2023-09-07 10:00', null, '42942942442', null, 1, 1, 2);

insert into offer_order (price, quantity, offer_id, order_id, rated) values
        (200, 2, 1, 1, false);

insert into review (date, rate, text, offer_id, user_id) values
                    ('2023-09-07 12:00', 5, 'Odlicno sve, super, bravo za uslugu.', 1, 2);
