--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: carwash; Type: COMMENT; Schema: -; Owner: pauls
--

COMMENT ON DATABASE carwash IS 'База учебного проекта Java WEB';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cities; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE cities (
    id_city integer NOT NULL,
    name character varying(255) NOT NULL,
    region character varying(255)
);


ALTER TABLE cities OWNER TO root;

--
-- Name: Cities_id_city_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE "Cities_id_city_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Cities_id_city_seq" OWNER TO root;

--
-- Name: Cities_id_city_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE "Cities_id_city_seq" OWNED BY cities.id_city;


--
-- Name: addr; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE addr (
    id_addr integer NOT NULL,
    street character varying(255),
    building character varying(10),
    letter character varying(24),
    latitude numeric(15,10),
    longitude numeric(15,10),
    id_city integer DEFAULT 1 NOT NULL
);


ALTER TABLE addr OWNER TO root;

--
-- Name: addr_id_addr_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE addr_id_addr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE addr_id_addr_seq OWNER TO root;

--
-- Name: addr_id_addr_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE addr_id_addr_seq OWNED BY addr.id_addr;


--
-- Name: box_status; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE box_status (
    id_status integer NOT NULL,
    code character varying(8) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE box_status OWNER TO root;

--
-- Name: box_status_id_status_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE box_status_id_status_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE box_status_id_status_seq OWNER TO root;

--
-- Name: box_status_id_status_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE box_status_id_status_seq OWNED BY box_status.id_status;


--
-- Name: box_types; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE box_types (
    id_type integer NOT NULL,
    type_code character varying(20) NOT NULL,
    type_name character varying(255),
    descr character varying(255)
);


ALTER TABLE box_types OWNER TO root;

--
-- Name: box_types_id_type_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE box_types_id_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE box_types_id_type_seq OWNER TO root;

--
-- Name: box_types_id_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE box_types_id_type_seq OWNED BY box_types.id_type;


--
-- Name: car_types; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE car_types (
    id_type integer NOT NULL,
    type_code character varying(20) NOT NULL,
    type_name character varying(255),
    descr character varying(255)
);


ALTER TABLE car_types OWNER TO root;

--
-- Name: car_types_id_type_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE car_types_id_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_types_id_type_seq OWNER TO root;

--
-- Name: car_types_id_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE car_types_id_type_seq OWNED BY car_types.id_type;


--
-- Name: cars; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE cars (
    id_car bigint NOT NULL,
    id_car_type smallint NOT NULL,
    carnum character varying(12) NOT NULL,
    carmodel character varying(50) NOT NULL,
    description character varying(255) NOT NULL,
    status smallint,
    note character varying(255),
    date_reg timestamp with time zone DEFAULT now(),
    date_last_wash date
);


ALTER TABLE cars OWNER TO root;

--
-- Name: cars_id_car_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE cars_id_car_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cars_id_car_seq OWNER TO root;

--
-- Name: cars_id_car_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE cars_id_car_seq OWNED BY cars.id_car;


--
-- Name: chem_clean_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE chem_clean_services (
    id_service integer NOT NULL,
    id_dirt_type integer NOT NULL,
    id_material integer DEFAULT 0 NOT NULL,
    cost numeric(10,2) DEFAULT 0 NOT NULL,
    duration interval(6) DEFAULT '00:05:00'::interval(6) NOT NULL
);


ALTER TABLE chem_clean_services OWNER TO root;

--
-- Name: clean_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE clean_services (
    id_service integer NOT NULL,
    id_dirt_type integer NOT NULL,
    cost numeric(10,2) DEFAULT 0 NOT NULL,
    duration interval(6) DEFAULT '00:05:00'::interval(6) NOT NULL
);


ALTER TABLE clean_services OWNER TO root;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE clients (
    id_client bigint NOT NULL,
    tel character varying(255),
    status smallint,
    date_reg timestamp with time zone DEFAULT now(),
    date_last_wash date,
    id_person bigint
);


ALTER TABLE clients OWNER TO root;

--
-- Name: clients_id_client_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE clients_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_client_seq OWNER TO root;

--
-- Name: clients_id_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE clients_id_client_seq OWNED BY clients.id_client;


--
-- Name: complex_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE complex_services (
    id_service integer NOT NULL,
    add_info character varying(255),
    cost numeric(10,2) DEFAULT 0 NOT NULL,
    duration interval(6) DEFAULT '00:05:00'::interval(6) NOT NULL
);


ALTER TABLE complex_services OWNER TO root;

--
-- Name: dirt_types; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE dirt_types (
    id_type integer NOT NULL,
    type_code character varying(255) NOT NULL,
    descr character varying(255),
    type_name character varying(255)
);


ALTER TABLE dirt_types OWNER TO root;

--
-- Name: dirt_types_id_type_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE dirt_types_id_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dirt_types_id_type_seq OWNER TO root;

--
-- Name: dirt_types_id_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE dirt_types_id_type_seq OWNED BY dirt_types.id_type;


--
-- Name: discounts; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE discounts (
    id_discount integer NOT NULL,
    discount_type integer NOT NULL,
    discount_name character varying(255) NOT NULL,
    discount_amount numeric(10,2)
);


ALTER TABLE discounts OWNER TO root;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO root;

--
-- Name: order_details; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE order_details (
    id_detail bigint NOT NULL,
    id_order bigint NOT NULL,
    id_service bigint NOT NULL,
    service_cost numeric(10,2) DEFAULT 0.00,
    id_work bigint
);


ALTER TABLE order_details OWNER TO root;

--
-- Name: order_detail_id_detail_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE order_detail_id_detail_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_detail_id_detail_seq OWNER TO root;

--
-- Name: order_detail_id_detail_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE order_detail_id_detail_seq OWNED BY order_details.id_detail;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE orders (
    id_order bigint NOT NULL,
    id_fclt integer NOT NULL,
    id_car integer NOT NULL,
    id_client integer NOT NULL,
    id_discount integer,
    add_info character varying(255),
    start_sum numeric(10,2),
    discount_sum numeric(10,2),
    final_sum numeric(10,2),
    is_prepaid boolean DEFAULT false,
    is_canceled boolean DEFAULT false,
    time_create timestamp without time zone NOT NULL,
    time_start timestamp without time zone,
    time_finish timestamp without time zone,
    time_canceled timestamp without time zone,
    time_payed timestamp without time zone,
    time_shedule timestamp without time zone
);


ALTER TABLE orders OWNER TO root;

--
-- Name: orders_id_order_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE orders_id_order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE orders_id_order_seq OWNER TO root;

--
-- Name: orders_id_order_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE orders_id_order_seq OWNED BY orders.id_order;


--
-- Name: other_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE other_services (
    id_service integer NOT NULL,
    add_info character varying(255),
    cost numeric(10,2) DEFAULT 0 NOT NULL,
    duration interval(6) DEFAULT '00:05:00'::interval(6) NOT NULL
);


ALTER TABLE other_services OWNER TO root;

--
-- Name: persons; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE persons (
    id_person bigint NOT NULL,
    first_name character varying(255),
    middle_name character varying(255),
    last_name character varying(255),
    birthdate date,
    email character varying(255)
);


ALTER TABLE persons OWNER TO root;

--
-- Name: person_id_person_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE person_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_person_seq OWNER TO root;

--
-- Name: person_id_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE person_id_person_seq OWNED BY persons.id_person;


--
-- Name: phones; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE phones (
    id_phone bigint NOT NULL,
    phone_number character varying(255),
    id_person integer
);


ALTER TABLE phones OWNER TO root;

--
-- Name: phones_id_phone_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE phones_id_phone_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE phones_id_phone_seq OWNER TO root;

--
-- Name: phones_id_phone_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE phones_id_phone_seq OWNED BY phones.id_phone;


--
-- Name: placemark_yandex; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE placemark_yandex (
    id integer NOT NULL,
    ballooncontent character varying(255),
    hintcontent character varying(255),
    latitude double precision NOT NULL,
    longitude double precision NOT NULL
);


ALTER TABLE placemark_yandex OWNER TO root;

--
-- Name: placemark_yandex_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE placemark_yandex_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE placemark_yandex_id_seq OWNER TO root;

--
-- Name: placemark_yandex_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE placemark_yandex_id_seq OWNED BY placemark_yandex.id;


--
-- Name: polish_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE polish_services (
    id_service integer NOT NULL,
    add_info character varying(255),
    cost numeric(10,2) DEFAULT 0 NOT NULL,
    duration interval(6) DEFAULT '00:05:00'::interval(6) NOT NULL
);


ALTER TABLE polish_services OWNER TO root;

--
-- Name: products; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE products (
    id bigint NOT NULL,
    active boolean NOT NULL,
    name character varying(255),
    price double precision NOT NULL
);


ALTER TABLE products OWNER TO root;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_id_seq OWNER TO root;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE products_id_seq OWNED BY products.id;


--
-- Name: products_sales; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE products_sales (
    id bigint NOT NULL,
    product_count smallint,
    product_id bigint,
    sale_id bigint
);


ALTER TABLE products_sales OWNER TO root;

--
-- Name: products_sales_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE products_sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_sales_id_seq OWNER TO root;

--
-- Name: products_sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE products_sales_id_seq OWNED BY products_sales.id;


--
-- Name: r_client_car; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE r_client_car (
    id_client integer NOT NULL,
    id_car integer NOT NULL
);


ALTER TABLE r_client_car OWNER TO root;

--
-- Name: r_complex_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE r_complex_services (
    id_complex integer NOT NULL,
    id_service integer NOT NULL
);


ALTER TABLE r_complex_services OWNER TO root;

--
-- Name: r_order_discount; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE r_order_discount (
    id_order bigint NOT NULL,
    id_discount bigint NOT NULL
);


ALTER TABLE r_order_discount OWNER TO root;

--
-- Name: r_user_roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE r_user_roles (
    id_user integer NOT NULL,
    id_role integer NOT NULL
);


ALTER TABLE r_user_roles OWNER TO root;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: pauls
--

CREATE TABLE roles (
    id_role integer NOT NULL,
    role_name character varying(255),
    description character varying(255)
);


ALTER TABLE roles OWNER TO root;

--
-- Name: roles_id_role_seq; Type: SEQUENCE; Schema: public; Owner: pauls
--

CREATE SEQUENCE roles_id_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE roles_id_role_seq OWNER TO root;

--
-- Name: roles_id_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pauls
--

ALTER SEQUENCE roles_id_role_seq OWNED BY roles.id_role;


--
-- Name: sales; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE sales (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    pay_date timestamp without time zone,
    price double precision NOT NULL,
    status character varying(255),
    client_id_client integer NOT NULL
);


ALTER TABLE sales OWNER TO root;

--
-- Name: sales_client_id_client_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE sales_client_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sales_client_id_client_seq OWNER TO root;

--
-- Name: sales_client_id_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE sales_client_id_client_seq OWNED BY sales.client_id_client;


--
-- Name: sales_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sales_id_seq OWNER TO root;

--
-- Name: sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE sales_id_seq OWNED BY sales.id;


--
-- Name: salon_materials; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE salon_materials (
    id_type integer NOT NULL,
    type_code character varying(20) NOT NULL,
    type_name character varying(255),
    descr character varying(255)
);


ALTER TABLE salon_materials OWNER TO root;

--
-- Name: salon_materials_id_type_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE salon_materials_id_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE salon_materials_id_type_seq OWNER TO root;

--
-- Name: salon_materials_id_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE salon_materials_id_type_seq OWNED BY salon_materials.id_type;


--
-- Name: service_discount_id_discount_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE service_discount_id_discount_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE service_discount_id_discount_seq OWNER TO root;

--
-- Name: service_discount_id_discount_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE service_discount_id_discount_seq OWNED BY discounts.id_discount;


--
-- Name: service_status; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE service_status (
    id_status integer NOT NULL,
    code character varying(8) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE service_status OWNER TO root;

--
-- Name: service_status_id_status_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE service_status_id_status_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE service_status_id_status_seq OWNER TO root;

--
-- Name: service_status_id_status_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE service_status_id_status_seq OWNED BY service_status.id_status;


--
-- Name: service_types; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE service_types (
    id_type integer NOT NULL,
    type_code character varying(255) NOT NULL,
    descr character varying(255),
    type_name character varying(255)
);


ALTER TABLE service_types OWNER TO root;

--
-- Name: service_types_id_type_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE service_types_id_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE service_types_id_type_seq OWNER TO root;

--
-- Name: service_types_id_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE service_types_id_type_seq OWNED BY service_types.id_type;


--
-- Name: services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE services (
    id_service integer NOT NULL,
    id_fclt integer NOT NULL,
    id_type integer NOT NULL,
    name character varying(255) NOT NULL,
    is_pack boolean DEFAULT false NOT NULL,
    id_status smallint DEFAULT 0 NOT NULL,
    description character varying(255),
    time_create timestamp without time zone DEFAULT now()
);


ALTER TABLE services OWNER TO root;

--
-- Name: services_id_service_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE services_id_service_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE services_id_service_seq OWNER TO root;

--
-- Name: services_id_service_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE services_id_service_seq OWNED BY services.id_service;


--
-- Name: users; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE users (
    id_user bigint NOT NULL,
    login character varying(255),
    password character varying(255),
    time_create timestamp without time zone,
    time_edit timestamp without time zone,
    id_person bigint
);


ALTER TABLE users OWNER TO root;

--
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_user_seq OWNER TO root;

--
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE users_id_user_seq OWNED BY users.id_user;


--
-- Name: v_chem_clean_services; Type: VIEW; Schema: public; Owner: pauls
--

CREATE VIEW v_chem_clean_services AS
 SELECT c.id_service,
    c.id_fclt,
    c.id_type,
    c.name,
    c.is_pack,
    c.id_status,
    c.description,
    c.time_create,
    cc.id_dirt_type,
    cc.id_material,
    cc.cost
   FROM (services c
     JOIN chem_clean_services cc ON ((c.id_service = cc.id_service)));


ALTER TABLE v_chem_clean_services OWNER TO pauls;

--
-- Name: v_clean_services; Type: VIEW; Schema: public; Owner: pauls
--

CREATE VIEW v_clean_services AS
 SELECT c.id_service,
    c.id_fclt,
    c.id_type,
    c.name,
    c.is_pack,
    c.id_status,
    c.description,
    c.time_create,
    cc.id_dirt_type,
    cc.cost
   FROM (services c
     JOIN clean_services cc ON ((c.id_service = cc.id_service)));


ALTER TABLE v_clean_services OWNER TO pauls;

--
-- Name: v_complex_services; Type: VIEW; Schema: public; Owner: pauls
--

CREATE VIEW v_complex_services AS
 SELECT c.id_service,
    c.id_fclt,
    c.id_type,
    c.name,
    c.is_pack,
    c.id_status,
    c.description,
    c.time_create,
    cc.add_info,
    cc.cost
   FROM (services c
     JOIN complex_services cc ON ((c.id_service = cc.id_service)));


ALTER TABLE v_complex_services OWNER TO pauls;

--
-- Name: v_other_services; Type: VIEW; Schema: public; Owner: pauls
--

CREATE VIEW v_other_services AS
 SELECT c.id_service,
    c.id_fclt,
    c.id_type,
    c.name,
    c.is_pack,
    c.id_status,
    c.description,
    c.time_create,
    cc.add_info,
    cc.cost
   FROM (services c
     JOIN other_services cc ON ((c.id_service = cc.id_service)));


ALTER TABLE v_other_services OWNER TO pauls;

--
-- Name: v_polish_services; Type: VIEW; Schema: public; Owner: pauls
--

CREATE VIEW v_polish_services AS
 SELECT c.id_service,
    c.id_fclt,
    c.id_type,
    c.name,
    c.is_pack,
    c.id_status,
    c.description,
    c.time_create,
    cc.add_info,
    cc.cost
   FROM (services c
     JOIN polish_services cc ON ((c.id_service = cc.id_service)));


ALTER TABLE v_polish_services OWNER TO pauls;

--
-- Name: wash_services; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE wash_services (
    id_service integer NOT NULL,
    id_type_car integer NOT NULL,
    cost numeric(10,2) DEFAULT 0 NOT NULL,
    duration interval(6) DEFAULT '00:05:00'::interval(6) NOT NULL
);


ALTER TABLE wash_services OWNER TO root;

--
-- Name: COLUMN wash_services.duration; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN wash_services.duration IS 'planing wash duration';


--
-- Name: v_wash_services; Type: VIEW; Schema: public; Owner: pauls
--

CREATE VIEW v_wash_services AS
 SELECT c.id_service,
    c.id_fclt,
    c.id_type,
    c.name,
    c.is_pack,
    c.id_status,
    c.description,
    c.time_create,
    w.id_type_car,
    w.cost
   FROM (services c
     JOIN wash_services w ON ((c.id_service = w.id_service)));


ALTER TABLE v_wash_services OWNER TO pauls;

--
-- Name: wash_boxes; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE wash_boxes (
    id_box integer NOT NULL,
    name character varying(255),
    descr character varying(255),
    id_fclt integer,
    id_status smallint,
    id_type integer
);


ALTER TABLE wash_boxes OWNER TO root;

--
-- Name: wash_boxes_id_box_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE wash_boxes_id_box_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wash_boxes_id_box_seq OWNER TO root;

--
-- Name: wash_boxes_id_box_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE wash_boxes_id_box_seq OWNED BY wash_boxes.id_box;


--
-- Name: wash_facilities; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE wash_facilities (
    id_fclt integer NOT NULL,
    descr character varying(255),
    id_addr integer,
    id_manager integer,
    id_net integer,
    name character varying(255)
);


ALTER TABLE wash_facilities OWNER TO root;

--
-- Name: wash_facilities_id_fclt_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE wash_facilities_id_fclt_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wash_facilities_id_fclt_seq OWNER TO root;

--
-- Name: wash_facilities_id_fclt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE wash_facilities_id_fclt_seq OWNED BY wash_facilities.id_fclt;


--
-- Name: wash_nets; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE wash_nets (
    id_net integer NOT NULL,
    net_name character varying(255)[],
    descr character varying(255)[],
    id_owner integer
);


ALTER TABLE wash_nets OWNER TO root;

--
-- Name: wash_net_id_net_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE wash_net_id_net_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wash_net_id_net_seq OWNER TO root;

--
-- Name: wash_net_id_net_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE wash_net_id_net_seq OWNED BY wash_nets.id_net;


--
-- Name: works; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE works (
    id_work bigint NOT NULL,
    id_order bigint NOT NULL,
    id_service bigint NOT NULL,
    id_box bigint NOT NULL,
    id_manager bigint NOT NULL,
    time_start timestamp without time zone,
    time_plan_finish timestamp without time zone,
    time_finish timestamp without time zone,
    id_worker bigint NOT NULL,
    add_info character varying(255) NOT NULL
);


ALTER TABLE works OWNER TO root;

--
-- Name: work_id_work_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE work_id_work_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE work_id_work_seq OWNER TO root;

--
-- Name: work_id_work_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE work_id_work_seq OWNED BY works.id_work;


--
-- Name: addr id_addr; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY addr ALTER COLUMN id_addr SET DEFAULT nextval('addr_id_addr_seq'::regclass);


--
-- Name: box_status id_status; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY box_status ALTER COLUMN id_status SET DEFAULT nextval('box_status_id_status_seq'::regclass);


--
-- Name: box_types id_type; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY box_types ALTER COLUMN id_type SET DEFAULT nextval('box_types_id_type_seq'::regclass);


--
-- Name: car_types id_type; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY car_types ALTER COLUMN id_type SET DEFAULT nextval('car_types_id_type_seq'::regclass);


--
-- Name: cars id_car; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY cars ALTER COLUMN id_car SET DEFAULT nextval('cars_id_car_seq'::regclass);


--
-- Name: cities id_city; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY cities ALTER COLUMN id_city SET DEFAULT nextval('"Cities_id_city_seq"'::regclass);


--
-- Name: clients id_client; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY clients ALTER COLUMN id_client SET DEFAULT nextval('clients_id_client_seq'::regclass);


--
-- Name: dirt_types id_type; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY dirt_types ALTER COLUMN id_type SET DEFAULT nextval('dirt_types_id_type_seq'::regclass);


--
-- Name: discounts id_discount; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY discounts ALTER COLUMN id_discount SET DEFAULT nextval('service_discount_id_discount_seq'::regclass);


--
-- Name: order_details id_detail; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY order_details ALTER COLUMN id_detail SET DEFAULT nextval('order_detail_id_detail_seq'::regclass);


--
-- Name: orders id_order; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY orders ALTER COLUMN id_order SET DEFAULT nextval('orders_id_order_seq'::regclass);


--
-- Name: persons id_person; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY persons ALTER COLUMN id_person SET DEFAULT nextval('person_id_person_seq'::regclass);


--
-- Name: phones id_phone; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY phones ALTER COLUMN id_phone SET DEFAULT nextval('phones_id_phone_seq'::regclass);


--
-- Name: placemark_yandex id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY placemark_yandex ALTER COLUMN id SET DEFAULT nextval('placemark_yandex_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY products ALTER COLUMN id SET DEFAULT nextval('products_id_seq'::regclass);


--
-- Name: products_sales id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY products_sales ALTER COLUMN id SET DEFAULT nextval('products_sales_id_seq'::regclass);


--
-- Name: roles id_role; Type: DEFAULT; Schema: public; Owner: pauls
--

ALTER TABLE ONLY roles ALTER COLUMN id_role SET DEFAULT nextval('roles_id_role_seq'::regclass);


--
-- Name: sales id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY sales ALTER COLUMN id SET DEFAULT nextval('sales_id_seq'::regclass);


--
-- Name: sales client_id_client; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY sales ALTER COLUMN client_id_client SET DEFAULT nextval('sales_client_id_client_seq'::regclass);


--
-- Name: salon_materials id_type; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY salon_materials ALTER COLUMN id_type SET DEFAULT nextval('salon_materials_id_type_seq'::regclass);


--
-- Name: service_status id_status; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY service_status ALTER COLUMN id_status SET DEFAULT nextval('service_status_id_status_seq'::regclass);


--
-- Name: service_types id_type; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY service_types ALTER COLUMN id_type SET DEFAULT nextval('service_types_id_type_seq'::regclass);


--
-- Name: services id_service; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY services ALTER COLUMN id_service SET DEFAULT nextval('services_id_service_seq'::regclass);


--
-- Name: users id_user; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY users ALTER COLUMN id_user SET DEFAULT nextval('users_id_user_seq'::regclass);


--
-- Name: wash_boxes id_box; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_boxes ALTER COLUMN id_box SET DEFAULT nextval('wash_boxes_id_box_seq'::regclass);


--
-- Name: wash_facilities id_fclt; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_facilities ALTER COLUMN id_fclt SET DEFAULT nextval('wash_facilities_id_fclt_seq'::regclass);


--
-- Name: wash_nets id_net; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_nets ALTER COLUMN id_net SET DEFAULT nextval('wash_net_id_net_seq'::regclass);


--
-- Name: works id_work; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY works ALTER COLUMN id_work SET DEFAULT nextval('work_id_work_seq'::regclass);


--
-- Name: Cities_id_city_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('"Cities_id_city_seq"', 2, true);


--
-- Data for Name: addr; Type: TABLE DATA; Schema: public; Owner: root
--

COPY addr (id_addr, street, building, letter, latitude, longitude, id_city) FROM stdin;
1	Пресня	10	\N	50.5678900000	60.6543320000	1
2	Мойка	3	а	60.6578000000	50.3456700000	2
\.


--
-- Name: addr_id_addr_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('addr_id_addr_seq', 2, true);


--
-- Data for Name: box_status; Type: TABLE DATA; Schema: public; Owner: root
--

COPY box_status (id_status, code, name) FROM stdin;
1	WORKING	Работает
2	CLOSED	Закрыт
3	BUSY	Занят
4	BREAK	Перерыв
5	REPAIR	Ремонт
\.


--
-- Name: box_status_id_status_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('box_status_id_status_seq', 5, true);


--
-- Data for Name: box_types; Type: TABLE DATA; Schema: public; Owner: root
--

COPY box_types (id_type, type_code, type_name, descr) FROM stdin;
2	MEDIUM	Средний	\N
3	BIG	Большой	\N
4	AUTO	Автоматический	\N
5	SELF	Самообслуживание	\N
1	SMALL	Малый	Бокс только для леговых и SUV
\.


--
-- Name: box_types_id_type_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('box_types_id_type_seq', 5, true);


--
-- Data for Name: car_types; Type: TABLE DATA; Schema: public; Owner: root
--

COPY car_types (id_type, type_code, type_name, descr) FROM stdin;
1	CAR	Легковая	\N
2	SUV	Паркетник	\N
3	OFFROAD	Внедорожник	Рамный
4	LORY	Грузовик	\N
5	MINIBUS	Микроавтобус	\N
6	BUS	Автобус	\N
7	TRAK	Тягач	\N
\.


--
-- Name: car_types_id_type_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('car_types_id_type_seq', 7, true);


--
-- Data for Name: cars; Type: TABLE DATA; Schema: public; Owner: root
--

COPY cars (id_car, id_car_type, carnum, carmodel, description, status, note, date_reg, date_last_wash) FROM stdin;
1	1	М335РО99RUS	reno	--	1	\N	\N	\N
3	2	Р982ЕУ39RUS	KIA Sportage	серая	1	\N	\N	\N
\.


--
-- Name: cars_id_car_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('cars_id_car_seq', 3, true);


--
-- Data for Name: chem_clean_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY chem_clean_services (id_service, id_dirt_type, id_material, cost, duration) FROM stdin;
9	1	1	1000.00	00:05:00
9	2	1	1500.00	00:05:00
9	3	1	5000.00	00:05:00
9	1	2	1500.00	00:05:00
9	2	2	3000.00	00:05:00
9	3	2	5000.00	00:05:00
10	1	1	300.00	00:05:00
10	1	2	600.00	00:05:00
10	2	1	500.00	00:05:00
10	2	2	1000.00	00:05:00
\.


--
-- Data for Name: cities; Type: TABLE DATA; Schema: public; Owner: root
--

COPY cities (id_city, name, region) FROM stdin;
1	Москва	Москва
2	Санкт-Петербург	Ленинградская область
\.


--
-- Data for Name: clean_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY clean_services (id_service, id_dirt_type, cost, duration) FROM stdin;
5	1	500.00	00:05:00
5	2	1000.00	00:05:00
5	3	2000.00	00:05:00
\.


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: root
--

COPY clients (id_client, tel, status, date_reg, date_last_wash, id_person) FROM stdin;
3	962-845-97-21	1	2017-02-04 00:00:00+02	\N	3
2	387-896-54-74	2	2017-02-03 00:00:00+02	2017-02-17	2
1	495-963-98-74	1	2017-02-01 00:00:00+02	2017-02-01	1
\.


--
-- Name: clients_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('clients_id_client_seq', 3, true);


--
-- Data for Name: complex_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY complex_services (id_service, add_info, cost, duration) FROM stdin;
\.


--
-- Data for Name: dirt_types; Type: TABLE DATA; Schema: public; Owner: root
--

COPY dirt_types (id_type, type_code, descr, type_name) FROM stdin;
1	NORM	\N	Обычное
2	VERY	\N	Сильно загрязненная
3	HARD	\N	Очень грязная
4	VERY HARD	Кровь и мозги по салону	Очень-очень сильно
\.


--
-- Name: dirt_types_id_type_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('dirt_types_id_type_seq', 4, true);


--
-- Data for Name: discounts; Type: TABLE DATA; Schema: public; Owner: root
--

COPY discounts (id_discount, discount_type, discount_name, discount_amount) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('hibernate_sequence', 42, true);


--
-- Name: order_detail_id_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('order_detail_id_detail_seq', 1, false);


--
-- Data for Name: order_details; Type: TABLE DATA; Schema: public; Owner: root
--

COPY order_details (id_detail, id_order, id_service, service_cost, id_work) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: root
--

COPY orders (id_order, id_fclt, id_car, id_client, id_discount, add_info, start_sum, discount_sum, final_sum, is_prepaid, is_canceled, time_create, time_start, time_finish, time_canceled, time_payed, time_shedule) FROM stdin;
\.


--
-- Name: orders_id_order_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('orders_id_order_seq', 1, false);


--
-- Data for Name: other_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY other_services (id_service, add_info, cost, duration) FROM stdin;
12	Мойка 3ч колесных велтков	200.00	00:02:00
\.


--
-- Name: person_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('person_id_person_seq', 3, true);


--
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: root
--

COPY persons (id_person, first_name, middle_name, last_name, birthdate, email) FROM stdin;
1	Марфа	Петровна	Горбункова	\N	\N
2	Петр	Иванович	Сидоров	\N	\N
3	Семен	Семеныч	Горбунков	\N	\N
\.


--
-- Data for Name: phones; Type: TABLE DATA; Schema: public; Owner: root
--

COPY phones (id_phone, phone_number, id_person) FROM stdin;
3	495-963-98-74	3
1	962-845-97-21	1
2	387-896-54-74	2
\.


--
-- Name: phones_id_phone_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('phones_id_phone_seq', 3, true);


--
-- Data for Name: placemark_yandex; Type: TABLE DATA; Schema: public; Owner: root
--

COPY placemark_yandex (id, ballooncontent, hintcontent, latitude, longitude) FROM stdin;
\.


--
-- Name: placemark_yandex_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('placemark_yandex_id_seq', 1, false);


--
-- Data for Name: polish_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY polish_services (id_service, add_info, cost, duration) FROM stdin;
6	Полировка целым кирпичом	1000.00	00:05:00
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: root
--

COPY products (id, active, name, price) FROM stdin;
\.


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('products_id_seq', 1, false);


--
-- Data for Name: products_sales; Type: TABLE DATA; Schema: public; Owner: root
--

COPY products_sales (id, product_count, product_id, sale_id) FROM stdin;
\.


--
-- Name: products_sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('products_sales_id_seq', 1, false);


--
-- Data for Name: r_client_car; Type: TABLE DATA; Schema: public; Owner: root
--

COPY r_client_car (id_client, id_car) FROM stdin;
2	3
1	1
3	1
\.


--
-- Data for Name: r_complex_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY r_complex_services (id_complex, id_service) FROM stdin;
\.


--
-- Data for Name: r_order_discount; Type: TABLE DATA; Schema: public; Owner: root
--

COPY r_order_discount (id_order, id_discount) FROM stdin;
\.


--
-- Data for Name: r_user_roles; Type: TABLE DATA; Schema: public; Owner: root
--

COPY r_user_roles (id_user, id_role) FROM stdin;
1	1
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: pauls
--

COPY roles (id_role, role_name, description) FROM stdin;
1	DB	\N
2	ROLE_CLIENT	Base client role
3	ROLE_ADMIN	Base admin role
\.


--
-- Name: roles_id_role_seq; Type: SEQUENCE SET; Schema: public; Owner: pauls
--

SELECT pg_catalog.setval('roles_id_role_seq', 3, true);


--
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: root
--

COPY sales (id, creation_date, pay_date, price, status, client_id_client) FROM stdin;
\.


--
-- Name: sales_client_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('sales_client_id_client_seq', 1, false);


--
-- Name: sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('sales_id_seq', 1, false);


--
-- Data for Name: salon_materials; Type: TABLE DATA; Schema: public; Owner: root
--

COPY salon_materials (id_type, type_code, type_name, descr) FROM stdin;
1	VELUR	Велюр	Салон из велюра
2	LETHER	Кожа	Салон из кожи
\.


--
-- Name: salon_materials_id_type_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('salon_materials_id_type_seq', 2, true);


--
-- Name: service_discount_id_discount_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('service_discount_id_discount_seq', 1, false);


--
-- Data for Name: service_status; Type: TABLE DATA; Schema: public; Owner: root
--

COPY service_status (id_status, code, name) FROM stdin;
1	ON	Действует
3	PLAN	Планируется
2	OUT	выведен эксплуатации
4	SUSPEND	Приостановлен
\.


--
-- Name: service_status_id_status_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('service_status_id_status_seq', 4, true);


--
-- Data for Name: service_types; Type: TABLE DATA; Schema: public; Owner: root
--

COPY service_types (id_type, type_code, descr, type_name) FROM stdin;
1	WASH		Мойка авто
2	CLEAN		Чистка салона
3	CHEM_CLEAN		Химчиска салона
4	POLISH		Полировка
5	COMPLEX		Комплексные услуги
6	OTHER		Прочие услуги
\.


--
-- Name: service_types_id_type_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('service_types_id_type_seq', 6, true);


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY services (id_service, id_fclt, id_type, name, is_pack, id_status, description, time_create) FROM stdin;
3	3	1	Ручная мойка машины	f	1	Обычная ручная мойка с шампунем	\N
5	3	2	Чиска салона	f	1	Обычная ручная чиска салона	\N
6	3	4	Полировка	f	1	Полировка кирпичом	\N
9	3	3	Химчиска салона	f	1	Полная химчиска салона	\N
10	3	3	Химчиска одного кресла	f	1	Химчмска кресла	\N
11	3	1	Бесконтактная мойка	f	1	Бесконтактная мойка	2017-03-04 00:38:15.209204
12	3	6	Мойка велосипедов	f	1	Мойка велосипедов	2017-03-13 20:57:44.767116
\.


--
-- Name: services_id_service_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('services_id_service_seq', 12, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: root
--

COPY users (id_user, login, password, time_create, time_edit, id_person) FROM stdin;
1	root	$2a$10$D9hb9X8K9hq7Rz7igwIe.uXvgwPOK8nSZm65gXh4e82DRKw.ysaWK	\N	\N	1
\.


--
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('users_id_user_seq', 1, true);


--
-- Data for Name: wash_boxes; Type: TABLE DATA; Schema: public; Owner: root
--

COPY wash_boxes (id_box, name, descr, id_fclt, id_status, id_type) FROM stdin;
29	Бокс № 1	Бокс № 1 Мойка на Помойке	8	1	2
30	Бокс № 2	Бокс № 2 Мойка на Помойке	8	1	2
32	Бокс № 4	Бокс № 4 Мойка на Помойке	8	1	2
9	№ 1	№ 1 Мока на Фонтанке	3	1	1
10	№ 2	№ 2 Мока на Фонтанке	3	2	2
11	№ 3	№ 3 Мока на Фонтанке	3	1	3
12	№ 4	№ 4 Мока на Фонтанке	3	1	2
31	Бокс № 3	Бокс № 3 Мойка на Помойке	8	1	4
38	Бокс № 1	Бокс № 1 Test REST мойка	11	1	2
39	Бокс № 2	Бокс № 2 Test REST мойка	11	1	2
40	Бокс № 3	Бокс № 3 Test REST мойка	11	1	2
41	Бокс № 4	Бокс № 4 Test REST мойка	11	1	2
\.


--
-- Name: wash_boxes_id_box_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('wash_boxes_id_box_seq', 42, true);


--
-- Data for Name: wash_facilities; Type: TABLE DATA; Schema: public; Owner: root
--

COPY wash_facilities (id_fclt, descr, id_addr, id_manager, id_net, name) FROM stdin;
3	не фонтан	2	1	1	Мойка на Фонтанке
8	моет тех, кто чешется	1	1	1	Мойка на Помойке
11	RESTовая тестовая мойка	1	1	1	Test REST мойка
\.


--
-- Name: wash_facilities_id_fclt_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('wash_facilities_id_fclt_seq', 12, true);


--
-- Name: wash_net_id_net_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('wash_net_id_net_seq', 1, true);


--
-- Data for Name: wash_nets; Type: TABLE DATA; Schema: public; Owner: root
--

COPY wash_nets (id_net, net_name, descr, id_owner) FROM stdin;
1	{Супер-мойки}	{"Тестовая сеть поечных-помоечных"}	\N
\.


--
-- Data for Name: wash_services; Type: TABLE DATA; Schema: public; Owner: root
--

COPY wash_services (id_service, id_type_car, cost, duration) FROM stdin;
11	1	420.00	00:05:00
3	1	350.00	00:20:00
3	3	600.00	00:45:00
3	2	450.00	00:30:00
\.


--
-- Name: work_id_work_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('work_id_work_seq', 1, false);


--
-- Data for Name: works; Type: TABLE DATA; Schema: public; Owner: root
--

COPY works (id_work, id_order, id_service, id_box, id_manager, time_start, time_plan_finish, time_finish, id_worker, add_info) FROM stdin;
\.


--
-- Name: cities Cities_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cities
    ADD CONSTRAINT "Cities_pkey" PRIMARY KEY (id_city);


--
-- Name: addr addr_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY addr
    ADD CONSTRAINT addr_pkey PRIMARY KEY (id_addr);


--
-- Name: box_status box_status_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY box_status
    ADD CONSTRAINT box_status_pkey PRIMARY KEY (id_status);


--
-- Name: box_types box_types_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY box_types
    ADD CONSTRAINT box_types_pkey PRIMARY KEY (id_type);


--
-- Name: car_types car_types_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY car_types
    ADD CONSTRAINT car_types_pkey PRIMARY KEY (id_type);


--
-- Name: cars cars_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id_car);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id_client);


--
-- Name: dirt_types dirt_types_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY dirt_types
    ADD CONSTRAINT dirt_types_pkey PRIMARY KEY (id_type);


--
-- Name: cars num; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cars
    ADD CONSTRAINT num UNIQUE (carnum);


--
-- Name: order_details order_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY order_details
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (id_detail);


--
-- Name: persons person_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY persons
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


--
-- Name: phones phone_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY phones
    ADD CONSTRAINT phone_pkey PRIMARY KEY (id_phone);


--
-- Name: chem_clean_services pk_chem_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY chem_clean_services
    ADD CONSTRAINT pk_chem_service PRIMARY KEY (id_service, id_dirt_type, id_material);


--
-- Name: clean_services pk_id_clean_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY clean_services
    ADD CONSTRAINT pk_id_clean_service PRIMARY KEY (id_service, id_dirt_type);


--
-- Name: r_complex_services pk_id_comlex_service_services; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_complex_services
    ADD CONSTRAINT pk_id_comlex_service_services PRIMARY KEY (id_complex, id_service);


--
-- Name: complex_services pk_id_complex_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY complex_services
    ADD CONSTRAINT pk_id_complex_service PRIMARY KEY (id_service);


--
-- Name: discounts pk_id_discount; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY discounts
    ADD CONSTRAINT pk_id_discount PRIMARY KEY (id_discount);


--
-- Name: orders pk_id_order; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_id_order PRIMARY KEY (id_order);


--
-- Name: other_services pk_id_other_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY other_services
    ADD CONSTRAINT pk_id_other_service PRIMARY KEY (id_service);


--
-- Name: polish_services pk_id_polish_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY polish_services
    ADD CONSTRAINT pk_id_polish_service PRIMARY KEY (id_service);


--
-- Name: roles pk_id_role; Type: CONSTRAINT; Schema: public; Owner: pauls
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT pk_id_role PRIMARY KEY (id_role);


--
-- Name: services pk_id_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY services
    ADD CONSTRAINT pk_id_service PRIMARY KEY (id_service);


--
-- Name: wash_services pk_id_wash_service; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_services
    ADD CONSTRAINT pk_id_wash_service PRIMARY KEY (id_service, id_type_car);


--
-- Name: placemark_yandex placemark_yandex_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY placemark_yandex
    ADD CONSTRAINT placemark_yandex_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: products_sales products_sales_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY products_sales
    ADD CONSTRAINT products_sales_pkey PRIMARY KEY (id);


--
-- Name: r_client_car r_cient_car_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_client_car
    ADD CONSTRAINT r_cient_car_pkey PRIMARY KEY (id_car, id_client);


--
-- Name: r_order_discount r_order_discount_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_order_discount
    ADD CONSTRAINT r_order_discount_pkey PRIMARY KEY (id_order, id_discount);


--
-- Name: r_user_roles r_user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_user_roles
    ADD CONSTRAINT r_user_roles_pkey PRIMARY KEY (id_user, id_role);


--
-- Name: sales sales_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);


--
-- Name: salon_materials salon_materials_types_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY salon_materials
    ADD CONSTRAINT salon_materials_types_pkey PRIMARY KEY (id_type);


--
-- Name: service_status service_status_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY service_status
    ADD CONSTRAINT service_status_pkey PRIMARY KEY (id_status);


--
-- Name: service_types service_types_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY service_types
    ADD CONSTRAINT service_types_pkey PRIMARY KEY (id_type);


--
-- Name: service_types uk_hmp6psb6cq5oto04ha7v31opq; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY service_types
    ADD CONSTRAINT uk_hmp6psb6cq5oto04ha7v31opq UNIQUE (type_code);


--
-- Name: dirt_types uk_kaopydsgojsib9sxf9epbwwyt; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY dirt_types
    ADD CONSTRAINT uk_kaopydsgojsib9sxf9epbwwyt UNIQUE (type_code);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- Name: wash_boxes wash_boxes_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_boxes
    ADD CONSTRAINT wash_boxes_pkey PRIMARY KEY (id_box);


--
-- Name: wash_facilities wash_facilities_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_facilities
    ADD CONSTRAINT wash_facilities_pkey PRIMARY KEY (id_fclt);


--
-- Name: wash_nets wash_net_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_nets
    ADD CONSTRAINT wash_net_pkey PRIMARY KEY (id_net);


--
-- Name: works work_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY works
    ADD CONSTRAINT work_pkey PRIMARY KEY (id_work);


--
-- Name: box_status_code_idx; Type: INDEX; Schema: public; Owner: root
--

CREATE UNIQUE INDEX box_status_code_idx ON box_status USING btree (code);


--
-- Name: box_types_code_idx; Type: INDEX; Schema: public; Owner: root
--

CREATE UNIQUE INDEX box_types_code_idx ON box_types USING btree (type_code);


--
-- Name: car_types_code_idx; Type: INDEX; Schema: public; Owner: root
--

CREATE UNIQUE INDEX car_types_code_idx ON box_types USING btree (type_code);


--
-- Name: idx_service_status; Type: INDEX; Schema: public; Owner: root
--

CREATE UNIQUE INDEX idx_service_status ON service_status USING btree (code);


--
-- Name: salon_materials_code_idx; Type: INDEX; Schema: public; Owner: root
--

CREATE UNIQUE INDEX salon_materials_code_idx ON salon_materials USING btree (type_code);


--
-- Name: r_client_car fk5dmmfkwjvo3unccgnhribqers; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_client_car
    ADD CONSTRAINT fk5dmmfkwjvo3unccgnhribqers FOREIGN KEY (id_car) REFERENCES cars(id_car);


--
-- Name: wash_boxes fk6iu0jpq6idlqv1wubipe5ujn8; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_boxes
    ADD CONSTRAINT fk6iu0jpq6idlqv1wubipe5ujn8 FOREIGN KEY (id_fclt) REFERENCES wash_facilities(id_fclt);


--
-- Name: products_sales fk8i3t2i49yfx0gasjh7fbpq47p; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY products_sales
    ADD CONSTRAINT fk8i3t2i49yfx0gasjh7fbpq47p FOREIGN KEY (sale_id) REFERENCES sales(id);


--
-- Name: cars fk_car_type; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY cars
    ADD CONSTRAINT fk_car_type FOREIGN KEY (id_car_type) REFERENCES car_types(id_type);


--
-- Name: wash_services fk_car_type; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_services
    ADD CONSTRAINT fk_car_type FOREIGN KEY (id_type_car) REFERENCES car_types(id_type);


--
-- Name: wash_services fk_id_service; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_services
    ADD CONSTRAINT fk_id_service FOREIGN KEY (id_service) REFERENCES services(id_service);


--
-- Name: polish_services fk_id_service; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY polish_services
    ADD CONSTRAINT fk_id_service FOREIGN KEY (id_service) REFERENCES services(id_service);


--
-- Name: other_services fk_id_service; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY other_services
    ADD CONSTRAINT fk_id_service FOREIGN KEY (id_service) REFERENCES services(id_service);


--
-- Name: complex_services fk_id_service; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY complex_services
    ADD CONSTRAINT fk_id_service FOREIGN KEY (id_service) REFERENCES services(id_service);


--
-- Name: clean_services fk_id_service; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY clean_services
    ADD CONSTRAINT fk_id_service FOREIGN KEY (id_service) REFERENCES services(id_service);


--
-- Name: phones fk_person_phone; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY phones
    ADD CONSTRAINT fk_person_phone FOREIGN KEY (id_person) REFERENCES persons(id_person);


--
-- Name: r_client_car fk_r_client_car_car; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_client_car
    ADD CONSTRAINT fk_r_client_car_car FOREIGN KEY (id_car) REFERENCES cars(id_car);


--
-- Name: r_client_car fk_r_client_car_client; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_client_car
    ADD CONSTRAINT fk_r_client_car_client FOREIGN KEY (id_client) REFERENCES clients(id_client);


--
-- Name: r_user_roles fk_roles; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_user_roles
    ADD CONSTRAINT fk_roles FOREIGN KEY (id_role) REFERENCES roles(id_role);


--
-- Name: chem_clean_services fk_salon_materials; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY chem_clean_services
    ADD CONSTRAINT fk_salon_materials FOREIGN KEY (id_material) REFERENCES salon_materials(id_type);


--
-- Name: services fk_service_status; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY services
    ADD CONSTRAINT fk_service_status FOREIGN KEY (id_status) REFERENCES service_status(id_status);


--
-- Name: services fk_service_type; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY services
    ADD CONSTRAINT fk_service_type FOREIGN KEY (id_type) REFERENCES service_types(id_type);


--
-- Name: chem_clean_services fk_services; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY chem_clean_services
    ADD CONSTRAINT fk_services FOREIGN KEY (id_service) REFERENCES services(id_service);


--
-- Name: r_user_roles fk_users; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_user_roles
    ADD CONSTRAINT fk_users FOREIGN KEY (id_user) REFERENCES users(id_user);


--
-- Name: wash_facilities fkddh6ohqsuqckwbal81drn0wwe; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_facilities
    ADD CONSTRAINT fkddh6ohqsuqckwbal81drn0wwe FOREIGN KEY (id_addr) REFERENCES addr(id_addr);


--
-- Name: r_client_car fkfippmyc85mj9mi97fd6e6a6sh; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY r_client_car
    ADD CONSTRAINT fkfippmyc85mj9mi97fd6e6a6sh FOREIGN KEY (id_client) REFERENCES clients(id_client);


--
-- Name: products_sales fkha9rpo4uwv3xhuvvpfkw86b8b; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY products_sales
    ADD CONSTRAINT fkha9rpo4uwv3xhuvvpfkw86b8b FOREIGN KEY (product_id) REFERENCES products(id);


--
-- Name: wash_boxes fkivvxk2cjp7y1hujob738imm2t; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_boxes
    ADD CONSTRAINT fkivvxk2cjp7y1hujob738imm2t FOREIGN KEY (id_status) REFERENCES box_status(id_status);


--
-- Name: wash_boxes fkj1mue38r24mw2h6ny6t4rxlrr; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY wash_boxes
    ADD CONSTRAINT fkj1mue38r24mw2h6ny6t4rxlrr FOREIGN KEY (id_type) REFERENCES box_types(id_type);


--
-- Name: sales fkoxcn0yl1n9ose3vjrg71e2piq; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY sales
    ADD CONSTRAINT fkoxcn0yl1n9ose3vjrg71e2piq FOREIGN KEY (client_id_client) REFERENCES clients(id_client);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: cities; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE cities FROM PUBLIC;
REVOKE ALL ON TABLE cities FROM root;
GRANT ALL ON TABLE cities TO root;
GRANT ALL ON TABLE cities TO PUBLIC;


--
-- Name: Cities_id_city_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE "Cities_id_city_seq" FROM PUBLIC;
REVOKE ALL ON SEQUENCE "Cities_id_city_seq" FROM root;
GRANT ALL ON SEQUENCE "Cities_id_city_seq" TO root;
GRANT ALL ON SEQUENCE "Cities_id_city_seq" TO PUBLIC;


--
-- Name: addr; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE addr FROM PUBLIC;
REVOKE ALL ON TABLE addr FROM root;
GRANT ALL ON TABLE addr TO root;
GRANT ALL ON TABLE addr TO PUBLIC;


--
-- Name: addr_id_addr_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE addr_id_addr_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE addr_id_addr_seq FROM root;
GRANT ALL ON SEQUENCE addr_id_addr_seq TO root;
GRANT ALL ON SEQUENCE addr_id_addr_seq TO PUBLIC;


--
-- Name: box_status; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE box_status FROM PUBLIC;
REVOKE ALL ON TABLE box_status FROM root;
GRANT ALL ON TABLE box_status TO root;


--
-- Name: box_status_id_status_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE box_status_id_status_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE box_status_id_status_seq FROM root;
GRANT ALL ON SEQUENCE box_status_id_status_seq TO root;


--
-- Name: box_types; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE box_types FROM PUBLIC;
REVOKE ALL ON TABLE box_types FROM root;
GRANT ALL ON TABLE box_types TO root;
GRANT ALL ON TABLE box_types TO PUBLIC;


--
-- Name: box_types_id_type_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE box_types_id_type_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE box_types_id_type_seq FROM root;
GRANT ALL ON SEQUENCE box_types_id_type_seq TO root;


--
-- Name: car_types; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE car_types FROM PUBLIC;
REVOKE ALL ON TABLE car_types FROM root;
GRANT ALL ON TABLE car_types TO root;


--
-- Name: cars; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE cars FROM PUBLIC;
REVOKE ALL ON TABLE cars FROM root;
GRANT ALL ON TABLE cars TO root;


--
-- Name: chem_clean_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE chem_clean_services FROM PUBLIC;
REVOKE ALL ON TABLE chem_clean_services FROM root;
GRANT ALL ON TABLE chem_clean_services TO root;
GRANT ALL ON TABLE chem_clean_services TO PUBLIC;


--
-- Name: clean_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE clean_services FROM PUBLIC;
REVOKE ALL ON TABLE clean_services FROM root;
GRANT ALL ON TABLE clean_services TO root;


--
-- Name: clients; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE clients FROM PUBLIC;
REVOKE ALL ON TABLE clients FROM root;
GRANT ALL ON TABLE clients TO root;


--
-- Name: complex_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE complex_services FROM PUBLIC;
REVOKE ALL ON TABLE complex_services FROM root;
GRANT ALL ON TABLE complex_services TO root;


--
-- Name: discounts; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE discounts FROM PUBLIC;
REVOKE ALL ON TABLE discounts FROM root;
GRANT ALL ON TABLE discounts TO root;


--
-- Name: orders; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE orders FROM PUBLIC;
REVOKE ALL ON TABLE orders FROM root;
GRANT ALL ON TABLE orders TO root;


--
-- Name: other_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE other_services FROM PUBLIC;
REVOKE ALL ON TABLE other_services FROM root;
GRANT ALL ON TABLE other_services TO root;


--
-- Name: phones; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE phones FROM PUBLIC;
REVOKE ALL ON TABLE phones FROM root;
GRANT ALL ON TABLE phones TO root;
GRANT ALL ON TABLE phones TO pauls;


--
-- Name: polish_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE polish_services FROM PUBLIC;
REVOKE ALL ON TABLE polish_services FROM root;
GRANT ALL ON TABLE polish_services TO root;


--
-- Name: r_client_car; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE r_client_car FROM PUBLIC;
REVOKE ALL ON TABLE r_client_car FROM root;
GRANT ALL ON TABLE r_client_car TO root;


--
-- Name: r_complex_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE r_complex_services FROM PUBLIC;
REVOKE ALL ON TABLE r_complex_services FROM root;
GRANT ALL ON TABLE r_complex_services TO root;


--
-- Name: r_user_roles; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE r_user_roles FROM PUBLIC;
REVOKE ALL ON TABLE r_user_roles FROM root;
GRANT ALL ON TABLE r_user_roles TO root;


--
-- Name: roles; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE roles FROM PUBLIC;
REVOKE ALL ON TABLE roles FROM pauls;
GRANT ALL ON TABLE roles TO pauls;
GRANT ALL ON TABLE roles TO root;


--
-- Name: roles_id_role_seq; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON SEQUENCE roles_id_role_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE roles_id_role_seq FROM pauls;
GRANT ALL ON SEQUENCE roles_id_role_seq TO pauls;
GRANT ALL ON SEQUENCE roles_id_role_seq TO root;


--
-- Name: salon_materials; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE salon_materials FROM PUBLIC;
REVOKE ALL ON TABLE salon_materials FROM root;
GRANT ALL ON TABLE salon_materials TO root;


--
-- Name: service_status; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE service_status FROM PUBLIC;
REVOKE ALL ON TABLE service_status FROM root;
GRANT ALL ON TABLE service_status TO root;


--
-- Name: service_types; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE service_types FROM PUBLIC;
REVOKE ALL ON TABLE service_types FROM root;
GRANT ALL ON TABLE service_types TO root;
GRANT ALL ON TABLE service_types TO pauls;
GRANT ALL ON TABLE service_types TO PUBLIC;


--
-- Name: service_types_id_type_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE service_types_id_type_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE service_types_id_type_seq FROM root;
GRANT ALL ON SEQUENCE service_types_id_type_seq TO root;
GRANT ALL ON SEQUENCE service_types_id_type_seq TO pauls;
GRANT ALL ON SEQUENCE service_types_id_type_seq TO PUBLIC;


--
-- Name: services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE services FROM PUBLIC;
REVOKE ALL ON TABLE services FROM root;
GRANT ALL ON TABLE services TO root;


--
-- Name: services_id_service_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE services_id_service_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE services_id_service_seq FROM root;
GRANT ALL ON SEQUENCE services_id_service_seq TO root;
GRANT ALL ON SEQUENCE services_id_service_seq TO PUBLIC;


--
-- Name: users; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE users FROM PUBLIC;
REVOKE ALL ON TABLE users FROM root;
GRANT ALL ON TABLE users TO root;


--
-- Name: users_id_user_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE users_id_user_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE users_id_user_seq FROM root;
GRANT ALL ON SEQUENCE users_id_user_seq TO root;


--
-- Name: v_chem_clean_services; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE v_chem_clean_services FROM PUBLIC;
REVOKE ALL ON TABLE v_chem_clean_services FROM pauls;
GRANT ALL ON TABLE v_chem_clean_services TO pauls;
GRANT ALL ON TABLE v_chem_clean_services TO root;
GRANT ALL ON TABLE v_chem_clean_services TO PUBLIC;


--
-- Name: v_clean_services; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE v_clean_services FROM PUBLIC;
REVOKE ALL ON TABLE v_clean_services FROM pauls;
GRANT ALL ON TABLE v_clean_services TO pauls;
GRANT ALL ON TABLE v_clean_services TO root;
GRANT ALL ON TABLE v_clean_services TO PUBLIC;


--
-- Name: v_complex_services; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE v_complex_services FROM PUBLIC;
REVOKE ALL ON TABLE v_complex_services FROM pauls;
GRANT ALL ON TABLE v_complex_services TO pauls;
GRANT ALL ON TABLE v_complex_services TO root;
GRANT ALL ON TABLE v_complex_services TO PUBLIC;


--
-- Name: v_other_services; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE v_other_services FROM PUBLIC;
REVOKE ALL ON TABLE v_other_services FROM pauls;
GRANT ALL ON TABLE v_other_services TO pauls;
GRANT ALL ON TABLE v_other_services TO root;
GRANT ALL ON TABLE v_other_services TO PUBLIC;


--
-- Name: v_polish_services; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE v_polish_services FROM PUBLIC;
REVOKE ALL ON TABLE v_polish_services FROM pauls;
GRANT ALL ON TABLE v_polish_services TO pauls;
GRANT ALL ON TABLE v_polish_services TO root;
GRANT ALL ON TABLE v_polish_services TO PUBLIC;


--
-- Name: wash_services; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE wash_services FROM PUBLIC;
REVOKE ALL ON TABLE wash_services FROM root;
GRANT ALL ON TABLE wash_services TO root;


--
-- Name: v_wash_services; Type: ACL; Schema: public; Owner: pauls
--

REVOKE ALL ON TABLE v_wash_services FROM PUBLIC;
REVOKE ALL ON TABLE v_wash_services FROM pauls;
GRANT ALL ON TABLE v_wash_services TO pauls;
GRANT ALL ON TABLE v_wash_services TO root;
GRANT ALL ON TABLE v_wash_services TO PUBLIC;


--
-- Name: wash_nets; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON TABLE wash_nets FROM PUBLIC;
REVOKE ALL ON TABLE wash_nets FROM root;
GRANT ALL ON TABLE wash_nets TO root;


--
-- Name: wash_net_id_net_seq; Type: ACL; Schema: public; Owner: root
--

REVOKE ALL ON SEQUENCE wash_net_id_net_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE wash_net_id_net_seq FROM root;
GRANT ALL ON SEQUENCE wash_net_id_net_seq TO root;


--
-- PostgreSQL database dump complete
--

