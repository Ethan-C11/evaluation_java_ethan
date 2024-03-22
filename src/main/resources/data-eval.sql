INSERT INTO role(nom, admin) VALUES ("Client", 0), ("Ouvrier", 0), ("Admin", 1);

INSERT INTO utilisateur(pseudo, mot_de_passe,role_id)
VALUES ("Client Joe",
        "$2y$10$LX9BDGlK1jLIvKpc1gv4Z.bfDbrJcfEpOMjOctGC3UDsaBVDd346q",
        1),
       ("Admin Jack",
        "$2y$10$LX9BDGlK1jLIvKpc1gv4Z.bfDbrJcfEpOMjOctGC3UDsaBVDd346q",
        3),
        ("Ouvrier Bob",
        "$2y$10$LX9BDGlK1jLIvKpc1gv4Z.bfDbrJcfEpOMjOctGC3UDsaBVDd346q",
        2),
       ("Ouvrier Bob Junior",
        "$2y$10$LX9BDGlK1jLIvKpc1gv4Z.bfDbrJcfEpOMjOctGC3UDsaBVDd346q",
        2);

INSERT INTO chantier(nom,adresse, proprietaire_id, directeur_id) VALUES ("Construction batiment","5 Rue Saint Vincent", 1, 2), ("Piscine", "37 cités des jardins", 1, 4);

INSERT INTO operation(nom, chantier_id, date, ouvrier_id) VALUES ("Construction fondations",1, "2024-03-22", 3),
                                                                 ("Préparer le terrain",2, "2024-03-26", 2);

INSERT INTO chantier_operations(chantier_id, operations_id) VALUES (1,1);

INSERT INTO tache(nom,temps_realisation) VALUES ("Creuser", 300),
                                                ("Couler la dalle", 200),
                                                ("Laisser durcir", 300),
                                                ("Determiner la position de la piscine", 60);

INSERT INTO operation_tache(operation_id, tache_id) VALUES (1,1),
                                                           (1,2),
                                                           (1,3),
                                                           (2,4);