--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-10-27 12:28:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 221195)
-- Name: eksponati; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eksponati (
    id integer NOT NULL,
    id_muzej integer,
    naziv text,
    tip text
);


ALTER TABLE public.eksponati OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 221194)
-- Name: eksponati_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.eksponati_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.eksponati_id_seq OWNER TO postgres;

--
-- TOC entry 4804 (class 0 OID 0)
-- Dependencies: 219
-- Name: eksponati_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.eksponati_id_seq OWNED BY public.eksponati.id;


--
-- TOC entry 218 (class 1259 OID 221186)
-- Name: muzeji; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.muzeji (
    id integer NOT NULL,
    naziv text,
    drzava text,
    grad text,
    godina_osnivanja integer,
    tip text,
    velicina_kolekcije integer,
    posjetitelji integer,
    izlozbeni_prostor integer,
    web_stranica text,
    online_setnja boolean
);


ALTER TABLE public.muzeji OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 221185)
-- Name: muzeji_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.muzeji_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.muzeji_id_seq OWNER TO postgres;

--
-- TOC entry 4805 (class 0 OID 0)
-- Dependencies: 217
-- Name: muzeji_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.muzeji_id_seq OWNED BY public.muzeji.id;


--
-- TOC entry 4645 (class 2604 OID 221198)
-- Name: eksponati id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eksponati ALTER COLUMN id SET DEFAULT nextval('public.eksponati_id_seq'::regclass);


--
-- TOC entry 4644 (class 2604 OID 221189)
-- Name: muzeji id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.muzeji ALTER COLUMN id SET DEFAULT nextval('public.muzeji_id_seq'::regclass);


--
-- TOC entry 4798 (class 0 OID 221195)
-- Dependencies: 220
-- Data for Name: eksponati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eksponati (id, id_muzej, naziv, tip) FROM stdin;
1	1	Mona Lisa	slika
3	2	Simuwu ding	arheoloski artefakt
4	2	Oslikana keramika kulture Yangshao	arheoloski artefakt
5	3	The Last Judgment	freska
6	3	The School of Athens	freska
7	4	La Malinche huipil	nosnja
8	4	Aztec Sunstone	skulptura
2	1	Venus de Milo	skulptura
9	5	The Elgin Marbles	skulptura
10	5	Rosetta Stone	stela
11	6	Command module Columbia	letjelica
12	6	1959 Chevrolet Corvette C1	automobil
14	7	The Milkmaid	slika
13	7	Girl in a Blue Dress	slika
15	8	The Three Graces	skulptura
16	8	Portrait of Nikolay Borisovich Yusupov	slika
17	9	La maja desnuda	slika
18	9	David and Goliath	slika
19	10	Pensive Bodhisattva	skulptura
20	10	Gyeongcheonsa Pagoda	skulptura
\.


--
-- TOC entry 4796 (class 0 OID 221186)
-- Dependencies: 218
-- Data for Name: muzeji; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.muzeji (id, naziv, drzava, grad, godina_osnivanja, tip, velicina_kolekcije, posjetitelji, izlozbeni_prostor, web_stranica, online_setnja) FROM stdin;
1	Louvre	Francuska	Pariz	1793	povijesni	615797	8900000	72735	https://www.louvre.fr/en	t
2	National Museum of China	Kina	Peking	2003	povijesni	1300000	6765000	65000	http://en.chnmuseum.cn/	t
3	Vatican Museums	Vatikan	Rim	1506	umjetnicki	70000	6800000	43000	https://www.museivaticani.va/	t
4	National Museum of Anthropology	Meksiko	Ciudad de Mexico	1964	arheoloski	600000	2636352	33000	https://www.mna.inah.gob.mx/	f
5	British Museum	Engleska	London	1753	povijesni	8000000	5820860	75000	https://www.britishmuseum.org/	t
6	National Air and Space Museum	SAD	Washington, D.C.	1946	znanstveni	60000	3100000	71000	https://airandspace.si.edu/	t
7	Rijksmuseum	Nizozemska	Amsterdam	1798	umjetnicki	1000000	2700000	12000	https://www.rijksmuseum.nl/en	t
8	The State Hermitage Museum	Rusija	Sankt-Peterburg	1764	umjetnicki	3000000	3273753	66842	https://www.hermitagemuseum.org/wps/portal/hermitage/	t
9	Prado Museum	Spanjolska	Madrid	1819	umjetnicki	20000	3337550	25000	https://www.museodelprado.es/en	t
10	National Museum of Korea	Juzna Koreja	Seoul	1945	povijesni	310000	4180000	27090	https://www.museum.go.kr/site/eng/home	t
\.


--
-- TOC entry 4806 (class 0 OID 0)
-- Dependencies: 219
-- Name: eksponati_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.eksponati_id_seq', 20, true);


--
-- TOC entry 4807 (class 0 OID 0)
-- Dependencies: 217
-- Name: muzeji_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.muzeji_id_seq', 10, true);


--
-- TOC entry 4649 (class 2606 OID 221202)
-- Name: eksponati eksponati_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eksponati
    ADD CONSTRAINT eksponati_pkey PRIMARY KEY (id);


--
-- TOC entry 4647 (class 2606 OID 221193)
-- Name: muzeji muzeji_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.muzeji
    ADD CONSTRAINT muzeji_pkey PRIMARY KEY (id);


-- Completed on 2024-10-27 12:28:54

--
-- PostgreSQL database dump complete
--

