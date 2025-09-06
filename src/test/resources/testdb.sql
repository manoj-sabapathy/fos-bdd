-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
	id int8 NOT NULL,
	type_id int4 NOT NULL,
	provider_id int4 NULL,
	member_id int4 NULL,
	latitude numeric(10, 7) NULL,
	longitude numeric(10, 7) NULL,
	street1 varchar(256) NOT NULL,
	street2 varchar(256) NULL,
	city varchar(128) NOT NULL,
	state varchar(5) NOT NULL,
	zip varchar(12) NOT NULL,
	data_source_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	geocoding_confidence int2 NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);
CREATE INDEX address_member_idx ON public.address USING btree (member_id);
CREATE INDEX address_provider_idx ON public.address USING btree (provider_id);


-- public.changelog definition

-- Drop table

-- DROP TABLE public.changelog;

CREATE TABLE public.changelog (
	id serial4 NOT NULL,
	"type" int2 NULL,
	"version" varchar(50) NULL,
	description varchar(200) NOT NULL,
	"name" varchar(300) NOT NULL,
	checksum varchar(32) NULL,
	installed_by varchar(100) NOT NULL,
	installed_on timestamp DEFAULT now() NOT NULL,
	success bool NOT NULL,
	CONSTRAINT changelog_pkey PRIMARY KEY (id)
);


-- public.client definition

-- Drop table

-- DROP TABLE public.client;

CREATE TABLE public.client (
	id int4 NOT NULL,
	"name" varchar(64) NOT NULL,
	short_name varchar(10) NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);


-- public.email definition

-- Drop table

-- DROP TABLE public.email;

CREATE TABLE public.email (
	id int8 NOT NULL,
	type_id int4 NOT NULL,
	address varchar(128) NOT NULL,
	member_id int4 NULL,
	provider_id int4 NULL,
	data_source_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT email_pkey PRIMARY KEY (id)
);
CREATE INDEX email_member_idx ON public.email USING btree (member_id);
CREATE INDEX email_provider_idx ON public.email USING btree (provider_id);


-- public.geozone definition

-- Drop table

-- DROP TABLE public.geozone;

CREATE TABLE public.geozone (
	id int4 NOT NULL,
	"name" varchar(128) NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	CONSTRAINT geozone_pkey PRIMARY KEY (id)
);


-- public.geozone_zip definition

-- Drop table

-- DROP TABLE public.geozone_zip;

CREATE TABLE public.geozone_zip (
	id int4 NOT NULL,
	geozone_id int4 NOT NULL,
	zip varchar(12) NOT NULL,
	state varchar(5) NOT NULL,
	service_location varchar(128) NULL,
	sublocation varchar(128) NULL,
	region varchar(64) NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	CONSTRAINT geozone_zip_pkey PRIMARY KEY (id)
);
CREATE INDEX geozone_zip_geozone_idx ON public.geozone_zip USING btree (geozone_id);
CREATE INDEX geozone_zip_on_zip_idx ON public.geozone_zip USING btree (zip);


-- public."member" definition

-- Drop table

-- DROP TABLE public."member";

CREATE TABLE public."member" (
	id int4 NOT NULL,
	client_id int4 NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT member_pkey PRIMARY KEY (id)
);


-- public.member_appointment definition

-- Drop table

-- DROP TABLE public.member_appointment;

CREATE TABLE public.member_appointment (
	id int4 NOT NULL,
	parent_id int4 NULL,
	calendar_slot_id int4 NULL,
	provider_id int4 NULL,
	member_id int4 NOT NULL,
	flexible bool NOT NULL,
	notes text NOT NULL,
	date_of_service timestamp NULL,
	status_id int4 NOT NULL,
	data_source_id int4 NULL,
	call_form_type_id int4 NULL,
	date_created timestamptz NULL,
	date_updated timestamptz NULL,
	reason_id int4 NULL,
	psc_action_id int4 NULL,
	time_slot_change_type_id int4 NULL,
	product_id int4 NULL,
	flexible_days bool NULL,
	product_sub_type_id int4 NULL,
	primary_clinician bool NULL,
	start_time timestamp NULL,
	end_time timestamp NULL,
	dispatch_indicator int4 NULL,
	requires_translation bool NULL,
	program_id int4 NULL,
	member_offer_id int4 NULL,
	narrow_start_time timestamp NULL,
	narrow_end_time timestamp NULL,
	appointment_start_time timestamp NULL,
	CONSTRAINT member_appointment_pkey PRIMARY KEY (id)
);
CREATE INDEX member_appointment_member ON public.member_appointment USING btree (member_id);


-- public.member_attribute definition

-- Drop table

-- DROP TABLE public.member_attribute;

CREATE TABLE public.member_attribute (
	id int8 NOT NULL,
	member_id int4 NOT NULL,
	type_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT member_attribute_pkey PRIMARY KEY (id)
);
CREATE INDEX member_attribute_member_idx ON public.member_attribute USING btree (member_id);


-- public.member_demographic definition

-- Drop table

-- DROP TABLE public.member_demographic;

CREATE TABLE public.member_demographic (
	id int4 NOT NULL,
	member_id int4 NOT NULL,
	"first" varchar(64) NOT NULL,
	middle varchar(64) NULL,
	"last" varchar(64) NOT NULL,
	data_source_id int4 NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT member_demographic_pkey PRIMARY KEY (id)
);
CREATE INDEX member_demographic_member_idx ON public.member_demographic USING btree (id);


-- public.optimizationdata definition

-- Drop table

-- DROP TABLE public.optimizationdata;

CREATE TABLE public.optimizationdata (
	id bigserial NOT NULL,
	vendoroptimizationid text NULL,
	inputjson jsonb NULL,
	outputjson jsonb NULL,
	status int4 NULL,
	dataprocessed int4 DEFAULT 0 NULL,
	rundate date NULL,
	optimizationtype int4 NULL,
	user_created text NULL,
	date_created timestamptz DEFAULT now() NOT NULL,
	date_updated timestamptz DEFAULT now() NOT NULL,
	CONSTRAINT optimizationdata_pkey PRIMARY KEY (id)
);


-- public.phone definition

-- Drop table

-- DROP TABLE public.phone;

CREATE TABLE public.phone (
	id int8 NOT NULL,
	type_id int4 NOT NULL,
	dial_number varchar(20) NULL,
	member_id int4 NULL,
	provider_id int4 NULL,
	data_source_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT phone_pkey PRIMARY KEY (id)
);
CREATE INDEX phone_member_idx ON public.phone USING btree (member_id);
CREATE INDEX phone_provider_idx ON public.phone USING btree (provider_id);


-- public.provider definition

-- Drop table

-- DROP TABLE public.provider;

CREATE TABLE public.provider (
	id int4 NOT NULL,
	employee_id varchar(128) NULL,
	"first" varchar(64) NOT NULL,
	middle varchar(64) NULL,
	"last" varchar(64) NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_pkey PRIMARY KEY (id)
);


-- public.provider_attribute definition

-- Drop table

-- DROP TABLE public.provider_attribute;

CREATE TABLE public.provider_attribute (
	id int8 NOT NULL,
	provider_id int4 NOT NULL,
	type_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_attribute_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_attribute_provider_idx ON public.provider_attribute USING btree (provider_id);


-- public.provider_availability definition

-- Drop table

-- DROP TABLE public.provider_availability;

CREATE TABLE public.provider_availability (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	week_day int4 NOT NULL,
	am_visits int4 NOT NULL,
	pm_visits int4 NOT NULL,
	evening_visits int4 NOT NULL,
	product_flags int4 NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_availability_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_availability_provider_idx ON public.provider_availability USING btree (provider_id);


-- public.provider_availability_override definition

-- Drop table

-- DROP TABLE public.provider_availability_override;

CREATE TABLE public.provider_availability_override (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	override_date timestamptz NOT NULL,
	am_visits int4 NOT NULL,
	pm_visits int4 NOT NULL,
	evening_visits int4 NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_availability_override_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_availability_override_provider_idx ON public.provider_availability_override USING btree (provider_id);


-- public.provider_client definition

-- Drop table

-- DROP TABLE public.provider_client;

CREATE TABLE public.provider_client (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	client_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	state varchar(2) NULL,
	CONSTRAINT provider_client_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_client_provider_idx ON public.provider_client USING btree (provider_id);


-- public.provider_employment_status definition

-- Drop table

-- DROP TABLE public.provider_employment_status;

CREATE TABLE public.provider_employment_status (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	status_id int4 NOT NULL,
	sub_type_id int4 NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_employment_status_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_employment_status_provider_idx ON public.provider_employment_status USING btree (provider_id);


-- public.provider_program definition

-- Drop table

-- DROP TABLE public.provider_program;

CREATE TABLE public.provider_program (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	program_id int4 NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_program_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_program_provider_idx ON public.provider_program USING btree (provider_id);


-- public.provider_role definition

-- Drop table

-- DROP TABLE public.provider_role;

CREATE TABLE public.provider_role (
	id int4 NOT NULL,
	type_id int4 NOT NULL,
	provider_id int4 NOT NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	CONSTRAINT provider_role_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_role_provider_idx ON public.provider_role USING btree (provider_id);


-- public.provider_schedule_location definition

-- Drop table

-- DROP TABLE public.provider_schedule_location;

CREATE TABLE public.provider_schedule_location (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	start_street1 varchar(60) NOT NULL,
	start_street2 varchar(60) NULL,
	start_city varchar(60) NOT NULL,
	start_state varchar(2) NOT NULL,
	start_zip varchar(10) NOT NULL,
	start_county varchar(60) NULL,
	end_street1 varchar(60) NOT NULL,
	end_street2 varchar(60) NULL,
	end_city varchar(60) NOT NULL,
	end_state varchar(2) NOT NULL,
	end_zip varchar(10) NOT NULL,
	end_county varchar(60) NULL,
	lunch_time int4 NULL,
	home_base_distance int4 NULL,
	visit_duration int4 NULL,
	active int4 NULL,
	signed_waiver int4 NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NULL,
	start_latitude numeric(10, 7) NULL,
	start_longitude numeric(10, 7) NULL,
	start_geocoding_confidence int2 NULL,
	end_latitude numeric(10, 7) NULL,
	end_longitude numeric(10, 7) NULL,
	end_geocoding_confidence int2 NULL,
	CONSTRAINT provider_schedule_location_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_schedule_location_provider_idx ON public.provider_schedule_location USING btree (provider_id);


-- public.provider_schedule_location_daily_override definition

-- Drop table

-- DROP TABLE public.provider_schedule_location_daily_override;

CREATE TABLE public.provider_schedule_location_daily_override (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	calendar_date date NOT NULL,
	start_street1 varchar(60) NULL,
	start_street2 varchar(60) NULL,
	start_city varchar(60) NULL,
	start_state varchar(2) NULL,
	start_county varchar(60) NULL,
	start_zip varchar(10) NULL,
	end_street1 varchar(60) NULL,
	end_street2 varchar(60) NULL,
	end_city varchar(60) NULL,
	end_state varchar(2) NULL,
	end_zip varchar(10) NULL,
	end_county varchar(60) NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NULL,
	start_latitude numeric(10, 7) NULL,
	start_longitude numeric(10, 7) NULL,
	start_geocoding_confidence int2 NULL,
	end_latitude numeric(10, 7) NULL,
	end_longitude numeric(10, 7) NULL,
	end_geocoding_confidence int2 NULL,
	CONSTRAINT provider_schedule_location_daily_override_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_schedule_location_daily_override_provider_idx ON public.provider_schedule_location_daily_override USING btree (provider_id);


-- public.provider_schedule_location_override definition

-- Drop table

-- DROP TABLE public.provider_schedule_location_override;

CREATE TABLE public.provider_schedule_location_override (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	start_street1 varchar(60) NULL,
	start_street2 varchar(60) NULL,
	start_city varchar(60) NULL,
	start_state varchar(2) NULL,
	start_zip varchar(10) NULL,
	start_county varchar(60) NULL,
	end_street1 varchar(60) NULL,
	end_street2 varchar(60) NULL,
	end_city varchar(60) NULL,
	end_state varchar(2) NULL,
	end_zip varchar(10) NULL,
	end_county varchar(60) NULL,
	actual_start_street1 varchar(60) NULL,
	actual_start_city varchar(60) NULL,
	actual_start_state varchar(2) NULL,
	actual_start_zip varchar(10) NULL,
	actual_start_county varchar(60) NULL,
	deleted bool NULL,
	start_date timestamptz NOT NULL,
	end_date timestamptz NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NULL,
	start_latitude numeric(10, 7) NULL,
	start_longitude numeric(10, 7) NULL,
	start_geocoding_confidence int2 NULL,
	end_latitude numeric(10, 7) NULL,
	end_longitude numeric(10, 7) NULL,
	end_geocoding_confidence int2 NULL,
	CONSTRAINT provider_schedule_location_override_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_schedule_location_override_provider_idx ON public.provider_schedule_location_override USING btree (provider_id);


-- public.provider_schedule_time definition

-- Drop table

-- DROP TABLE public.provider_schedule_time;

CREATE TABLE public.provider_schedule_time (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	start_street1 varchar(60) NULL,
	start_street2 varchar(60) NULL,
	start_city varchar(60) NULL,
	start_state varchar(2) NULL,
	start_county varchar(60) NULL,
	start_zip varchar(10) NULL,
	end_street1 varchar(60) NULL,
	end_street2 varchar(60) NULL,
	end_city varchar(60) NULL,
	end_state varchar(2) NULL,
	end_zip varchar(10) NULL,
	end_county varchar(60) NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NULL,
	working bool NOT NULL,
	day_of_week int4 NULL,
	preferred_start_time time NULL,
	preferred_end_time time NULL,
	preferred_start_time2 time NULL,
	preferred_end_time2 time NULL,
	start_latitude numeric(10, 7) NULL,
	start_longitude numeric(10, 7) NULL,
	start_geocoding_confidence int2 NULL,
	end_latitude numeric(10, 7) NULL,
	end_longitude numeric(10, 7) NULL,
	end_geocoding_confidence int2 NULL,
	CONSTRAINT provider_schedule_time_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_schedule_time_provider_idx ON public.provider_schedule_time USING btree (provider_id);


-- public.provider_schedule_time_override definition

-- Drop table

-- DROP TABLE public.provider_schedule_time_override;

CREATE TABLE public.provider_schedule_time_override (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	calendar_date date NULL,
	preferred_start_time time NULL,
	preferred_end_time time NULL,
	preferred_start_time2 time NULL,
	preferred_end_time2 time NULL,
	working bool NOT NULL,
	"comment" text NULL,
	reason_id int4 NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NULL,
	CONSTRAINT provider_schedule_time_override_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_schedule_time_override_provider_idx ON public.provider_schedule_time_override USING btree (provider_id);


-- public.provider_start_location definition

-- Drop table

-- DROP TABLE public.provider_start_location;

CREATE TABLE public.provider_start_location (
	id int4 NOT NULL,
	provider_id int4 NOT NULL,
	street1 varchar(128) NOT NULL,
	street2 varchar(128) NULL,
	city varchar(64) NOT NULL,
	state varchar(5) NOT NULL,
	zip varchar(12) NOT NULL,
	max_radius_miles int4 NULL,
	start_date date NULL,
	end_date date NULL,
	max_distance int4 NULL,
	last_modified timestamptz NULL,
	CONSTRAINT provider_start_location_pkey PRIMARY KEY (id)
);
CREATE INDEX provider_start_location_provider_idx ON public.provider_start_location USING btree (provider_id);


-- public.setting definition

-- Drop table

-- DROP TABLE public.setting;

CREATE TABLE public.setting (
	id serial4 NOT NULL,
	"name" varchar(64) NOT NULL,
	value varchar(64) NOT NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	active bool NOT NULL,
	category varchar(64) NULL,
	description varchar(64) NULL,
	CONSTRAINT setting_pkey PRIMARY KEY (id)
);


-- public.timezone definition

-- Drop table

-- DROP TABLE public.timezone;

CREATE TABLE public.timezone (
	zip varchar(10) NOT NULL,
	iana_id varchar(64) NOT NULL,
	utc_offset int4 NOT NULL,
	zone_name varchar(10) NOT NULL,
	dst bool NOT NULL,
	CONSTRAINT timezone_pkey PRIMARY KEY (zip)
);


-- public.work_order definition

-- Drop table

-- DROP TABLE public.work_order;

CREATE TABLE public.work_order (
	id bigserial NOT NULL,
	member_id int4 NULL,
	group_id uuid NOT NULL,
	status_id int4 NOT NULL,
	provider_id int4 NULL,
	priority int4 NOT NULL,
	optimized_start timestamp NULL,
	optimized_end timestamp NULL,
	optimized_sa varchar(12) NULL,
	travel_time int4 NULL,
	notes text NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	time_from_promised timestamp NULL,
	time_to_promised timestamp NULL,
	narrowed_start_time timestamptz NULL,
	narrowed_end_time timestamptz NULL,
	CONSTRAINT work_order_pkey PRIMARY KEY (id)
);
CREATE INDEX work_order_group_idx ON public.work_order USING btree (group_id);


-- public.work_order_state definition

-- Drop table

-- DROP TABLE public.work_order_state;

CREATE TABLE public.work_order_state (
	member_id int4 NOT NULL,
	status_id int4 NOT NULL,
	work_order_id int8 NOT NULL,
	CONSTRAINT work_order_state_pkey PRIMARY KEY (member_id)
);


-- public.zip_statistical_area definition

-- Drop table

-- DROP TABLE public.zip_statistical_area;

CREATE TABLE public.zip_statistical_area (
	zip varchar(12) NOT NULL,
	"type" varchar(10) NOT NULL,
	code int4 NOT NULL,
	"name" varchar(60) NOT NULL,
	county varchar(40) NULL,
	state bpchar(2) NULL,
	date_created timestamptz NOT NULL,
	date_updated timestamptz NOT NULL,
	multi_tz bool NULL,
	zone_name varchar(10) NULL,
	modified_name varchar(100) NULL,
	region varchar(10) NULL,
	CONSTRAINT zip_statistical_area_pkey PRIMARY KEY (zip)
);