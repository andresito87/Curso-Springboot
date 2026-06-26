INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience,
                           email, phone, linkedin_url, github_url)
VALUES ('Andrés', 'Podadera', 'Web & Mobile Apps Developer',
        'Apasionado por el desarrollo web y mobile con experiencia en Spring Boot, React y Android. Disfruto construyendo soluciones robustas, escalables y personalizadas.',
        'img/profile-image-andres.png', 5, 'andrespodadera87@gmail.com', '+34605163721',
        'https://www.linkedin.com/in/andresito87/', 'https://github.com/andresito87');

INSERT INTO skills (name, level_percentage, icon_class, personal_info_id)

VALUES ('HTML', 95, 'img/logos/html-5.png', 1),
       ('CSS', 90, 'img/logos/css-3.png', 1),
       ('Github', 85, 'img/logos/github.png', 1),
       ('JavaScript', 75, 'img/logos/js.png', 1),
       ('Java', 90, 'img/logos/java.png', 1),
       ('React', 70, 'img/logos/react.png', 1),
       ('Spring Boot', 85, 'img/logos/springboot.png', 1),
       ('PostgreSQL', 80, 'img/logos/postgre.png', 1),
       ('C#', 60, 'img/logos/csharp.png', 1),
       ('Python', 65, 'img/logos/python.png', 1);

INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id)
VALUES ('Ingeniería en Sistemas', 'Universidad XYZ', '2015-03-01', '2020-12-15',
        'Especialización en desarrollo de software y bases de datos.', 1),
       ('Curso de Spring Boot Avanzado', 'Plataforma ABC', '2021-01-10', '2021-06-30',
        'Profundización en microservicios y seguridad.', 1);

INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id)
VALUES ('Desarrollador Full Stack Mid-Senior', 'Tech Solutions S.A.', '2026-01-01', NULL,
        'Desarrollo y mantenimiento de aplicaciones empresariales tanto entornos web como mobile', 1),
       ('Desarrollador Frontend Junior', 'Innovatech Labs', '2025-09-01', '2025-12-31',
        'Participación en el desarrollo de webs e integraciones con apis.', 1);

INSERT INTO projects (title, description, image_url, project_url, personal_info_id)
VALUES ('Portfolio Personal', 'Un portafolio web para mostrar mis habilidades y proyectos.',
        'img/projects/project2.jpg', 'https://www.andrescoder.dev/es', 1),
       ('Aplicación de E-commerce', 'Plataforma de comercio electrónico con carrito de compras y pasarela de pago.',
        'img/projects/project1.jpg', 'https://github.com/andresito87/NutriSport', 1);