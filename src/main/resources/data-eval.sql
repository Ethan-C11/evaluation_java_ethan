INSERT INTO role(nom, admin) VALUES ("Client", 0), ("Ouvrier", 0), ("Admin", 1);

INSERT INTO utilisateur(pseudo, mot_de_passe,role_id)
VALUES ("Client Joe",
        "$2y$10$pvPHcxpP7IB3ltabQx7fOuW.Q4eUaZ6j7H9A1wIoUyBq4Y7dVw5Ze",
        1),
       ("Admin Jack",
        "$2y$10$LX9BDGlK1jLIvKpc1gv4Z.bfDbrJcfEpOMjOctGC3UDsaBVDd346q",
        3),
        ("Ouvrier Bob",
        "$2y$10$Te414p1UNCU5ILzus6p9TuQO7VsNxPpc3cGXmodNF/kfwICHGs362",
        2);

INSERT INTO chantier(nom,adresse, proprietaire_id, directeur_id) VALUES ("Construction batiment","5 Rue Saint Vincent", 1, 2);

INSERT INTO operation(nom, chantier_id, date, ouvrier_id) VALUES ("Construction fondations",1, "2024-03-22", 3);

INSERT INTO chantier_operations(chantier_id, operations_id) VALUES (1,1);