create database GYMS_SCHEDULE

CREATE TABLE gyms_schedule.Agendamentos (
	id INT auto_increment NOT NULL,
	`data` varchar(50) NOT NULL,
	horario varchar(20) not null,
	primary key (id)
)