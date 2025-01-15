-- Insert initial users
INSERT INTO user (email, password, fullname, type, number, balance, is_admin) VALUES
                                                                                   ('admin@admin.com', 'abc123', 'Admin Account', 'Savings Peso', '47290539480', 1000.00, TRUE),
                                                                                   ('jeff@gmail.com', 'abc123', 'Jeffrey de Lara', 'Savings Peso', '47290539481', 1029300.43, TRUE),
                                                                                   ('jeff2@gmail.com', 'abc123', 'Juan dela Cruz', 'Savings Peso', '47290539482', 392830.22, FALSE),
                                                                                   ('user24@gmail.com', 'abc123', 'Peter de Castro', 'Savings Peso', '47290539483', 102938.34, FALSE),
                                                                                   ('user34@gmail.com', 'abc123', 'Noli Enriquez', 'Checking Peso', '47290539484', 837495.38, FALSE),
                                                                                   ('derek@gmail.com', 'abc123', 'Karen Davila', 'Checking Peso', '47290539485', 574839.58, FALSE),
                                                                                   ('client@client.com', 'abc123', 'Client Demo Account', 'Savings Peso', '47290539486', 1000.00, FALSE);

-- Insert initial transactions
INSERT INTO transaction (user_id, title, amount, type, date) VALUES
                                                                  (3, 'Fund transfer', 2000.00, 'debit', '2021-10-01'),
                                                                  (3, 'Withdraw', 10000.00, 'debit', '2021-10-01');

-- Insert initial budgets
INSERT INTO budget (user_id, title, amount) VALUES
                                                 (3, 'Tuition fee', 12000.00),
                                                 (3, 'Food take out during the pandemic', 4000.00);
