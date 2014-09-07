--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

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
-- Name: animal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE animal (
    aninal_id bigint NOT NULL,
    caracteristicas character varying(255),
    cor character varying(255),
    especie character varying(255),
    nome character varying(255),
    raca character varying(255),
    cliente_id bigint
);


ALTER TABLE public.animal OWNER TO postgres;

--
-- Name: animal_aninal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE animal_aninal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.animal_aninal_id_seq OWNER TO postgres;

--
-- Name: animal_aninal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE animal_aninal_id_seq OWNED BY animal.aninal_id;


--
-- Name: atendimento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE atendimento (
    atendimeto_id bigint NOT NULL,
    quantidade integer,
    status character varying(255),
    valor real,
    animal_id bigint,
    funcionario_id bigint,
    servico_id bigint
);


ALTER TABLE public.atendimento OWNER TO postgres;

--
-- Name: atendimento_atendimeto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE atendimento_atendimeto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.atendimento_atendimeto_id_seq OWNER TO postgres;

--
-- Name: atendimento_atendimeto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE atendimento_atendimeto_id_seq OWNED BY atendimento.atendimeto_id;


--
-- Name: caixa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE caixa (
    caixa_id bigint NOT NULL,
    conta character varying(255),
    data date,
    horaabertura time without time zone,
    horafechamento time without time zone,
    status character varying(255),
    tipo character varying(255),
    valor real,
    funcionario_id bigint
);


ALTER TABLE public.caixa OWNER TO postgres;

--
-- Name: caixa_caixa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE caixa_caixa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caixa_caixa_id_seq OWNER TO postgres;

--
-- Name: caixa_caixa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE caixa_caixa_id_seq OWNED BY caixa.caixa_id;


--
-- Name: cargo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cargo (
    cargo_id bigint NOT NULL,
    atribuicoes character varying(255),
    nome character varying(255)
);


ALTER TABLE public.cargo OWNER TO postgres;

--
-- Name: cargo_cargo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cargo_cargo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cargo_cargo_id_seq OWNER TO postgres;

--
-- Name: cargo_cargo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cargo_cargo_id_seq OWNED BY cargo.cargo_id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    login character varying(255),
    senha character varying(255),
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: consulta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE consulta (
    consulta_id bigint NOT NULL,
    anotacoes character varying(255),
    prontuario character varying(255),
    status character varying(255),
    animal_id bigint,
    tipo_consulta_id bigint,
    medico_id bigint NOT NULL
);


ALTER TABLE public.consulta OWNER TO postgres;

--
-- Name: consulta_consulta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE consulta_consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consulta_consulta_id_seq OWNER TO postgres;

--
-- Name: consulta_consulta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE consulta_consulta_id_seq OWNED BY consulta.consulta_id;


--
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE endereco (
    pessoa_id bigint NOT NULL,
    bairro character varying(255),
    cep character varying(255),
    cidade character varying(255),
    complemento character varying(255),
    logradouro character varying(255),
    numero character varying(255),
    uf character varying(2),
    endereco_id integer NOT NULL
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- Name: endereco_endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE endereco_endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_endereco_id_seq OWNER TO postgres;

--
-- Name: endereco_endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE endereco_endereco_id_seq OWNED BY endereco.endereco_id;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE funcionario (
    admissao timestamp without time zone,
    ctps character varying(255),
    demissao timestamp without time zone,
    matricula character varying(255),
    salario real,
    status character varying(255),
    pessoa_id bigint NOT NULL,
    cargo_id bigint
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_pedido (
    produto bytea NOT NULL,
    pedido bytea NOT NULL,
    quantidade integer,
    valoritem real
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- Name: medico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE medico (
    anoformacao integer,
    crmv character varying(255),
    especialidade character varying(255),
    formacao character varying(255),
    horario character varying(255),
    turno character varying(255),
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.medico OWNER TO postgres;

--
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedido (
    pedido_id bigint NOT NULL,
    data date,
    status character varying(255),
    valorpedido real,
    cliente_id bigint NOT NULL
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- Name: pedido_pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pedido_pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedido_pedido_id_seq OWNER TO postgres;

--
-- Name: pedido_pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pedido_pedido_id_seq OWNED BY pedido.pedido_id;


--
-- Name: perfil; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE perfil (
    perfil_id bigint NOT NULL,
    authority character varying(255),
    nome character varying(255)
);


ALTER TABLE public.perfil OWNER TO postgres;

--
-- Name: perfil_perfil_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE perfil_perfil_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.perfil_perfil_id_seq OWNER TO postgres;

--
-- Name: perfil_perfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE perfil_perfil_id_seq OWNED BY perfil.perfil_id;


--
-- Name: perfil_permissao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE perfil_permissao (
    perfil_id bigint NOT NULL,
    permissao_id bigint NOT NULL
);


ALTER TABLE public.perfil_permissao OWNER TO postgres;

--
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE permissao (
    permissao_id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.permissao OWNER TO postgres;

--
-- Name: permissao_permissao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE permissao_permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permissao_permissao_id_seq OWNER TO postgres;

--
-- Name: permissao_permissao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE permissao_permissao_id_seq OWNED BY permissao.permissao_id;


--
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pessoa (
    pessoa_id bigint NOT NULL,
    celular character varying(255),
    cpf character varying(255),
    datanascimento date,
    email character varying(255),
    nome character varying(255),
    sexo character varying(255) NOT NULL,
    telefone character varying(255)
);


ALTER TABLE public.pessoa OWNER TO postgres;

--
-- Name: pessoa_pessoa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pessoa_pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pessoa_pessoa_id_seq OWNER TO postgres;

--
-- Name: pessoa_pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pessoa_pessoa_id_seq OWNED BY pessoa.pessoa_id;


--
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto (
    produto_id bigint NOT NULL,
    estoque integer,
    nome character varying(255),
    precocusto real,
    precovenda real
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- Name: produto_produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produto_produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_produto_id_seq OWNER TO postgres;

--
-- Name: produto_produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produto_produto_id_seq OWNED BY produto.produto_id;


--
-- Name: servico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE servico (
    servico_id bigint NOT NULL,
    descricao character varying(255),
    nome character varying(255),
    valor real
);


ALTER TABLE public.servico OWNER TO postgres;

--
-- Name: servico_servico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE servico_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servico_servico_id_seq OWNER TO postgres;

--
-- Name: servico_servico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE servico_servico_id_seq OWNED BY servico.servico_id;


--
-- Name: tipoconsulta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipoconsulta (
    tipo_consulta_id bigint NOT NULL,
    descricao character varying(255),
    preco real
);


ALTER TABLE public.tipoconsulta OWNER TO postgres;

--
-- Name: tipoconsulta_tipo_consulta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipoconsulta_tipo_consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipoconsulta_tipo_consulta_id_seq OWNER TO postgres;

--
-- Name: tipoconsulta_tipo_consulta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipoconsulta_tipo_consulta_id_seq OWNED BY tipoconsulta.tipo_consulta_id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    datacadastro timestamp without time zone,
    login character varying(255),
    senha character varying(255),
    status character varying(255),
    pessoa_id bigint NOT NULL,
    perfil_id bigint
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: aninal_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY animal ALTER COLUMN aninal_id SET DEFAULT nextval('animal_aninal_id_seq'::regclass);


--
-- Name: atendimeto_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atendimento ALTER COLUMN atendimeto_id SET DEFAULT nextval('atendimento_atendimeto_id_seq'::regclass);


--
-- Name: caixa_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caixa ALTER COLUMN caixa_id SET DEFAULT nextval('caixa_caixa_id_seq'::regclass);


--
-- Name: cargo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cargo ALTER COLUMN cargo_id SET DEFAULT nextval('cargo_cargo_id_seq'::regclass);


--
-- Name: consulta_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consulta ALTER COLUMN consulta_id SET DEFAULT nextval('consulta_consulta_id_seq'::regclass);


--
-- Name: endereco_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco ALTER COLUMN endereco_id SET DEFAULT nextval('endereco_endereco_id_seq'::regclass);


--
-- Name: pedido_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido ALTER COLUMN pedido_id SET DEFAULT nextval('pedido_pedido_id_seq'::regclass);


--
-- Name: perfil_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY perfil ALTER COLUMN perfil_id SET DEFAULT nextval('perfil_perfil_id_seq'::regclass);


--
-- Name: permissao_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissao ALTER COLUMN permissao_id SET DEFAULT nextval('permissao_permissao_id_seq'::regclass);


--
-- Name: pessoa_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa ALTER COLUMN pessoa_id SET DEFAULT nextval('pessoa_pessoa_id_seq'::regclass);


--
-- Name: produto_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto ALTER COLUMN produto_id SET DEFAULT nextval('produto_produto_id_seq'::regclass);


--
-- Name: servico_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servico ALTER COLUMN servico_id SET DEFAULT nextval('servico_servico_id_seq'::regclass);


--
-- Name: tipo_consulta_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipoconsulta ALTER COLUMN tipo_consulta_id SET DEFAULT nextval('tipoconsulta_tipo_consulta_id_seq'::regclass);


--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY animal (aninal_id, caracteristicas, cor, especie, nome, raca, cliente_id) FROM stdin;
\.


--
-- Name: animal_aninal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('animal_aninal_id_seq', 1, false);


--
-- Data for Name: atendimento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY atendimento (atendimeto_id, quantidade, status, valor, animal_id, funcionario_id, servico_id) FROM stdin;
\.


--
-- Name: atendimento_atendimeto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('atendimento_atendimeto_id_seq', 1, false);


--
-- Data for Name: caixa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY caixa (caixa_id, conta, data, horaabertura, horafechamento, status, tipo, valor, funcionario_id) FROM stdin;
\.


--
-- Name: caixa_caixa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('caixa_caixa_id_seq', 1, false);


--
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cargo (cargo_id, atribuicoes, nome) FROM stdin;
\.


--
-- Name: cargo_cargo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cargo_cargo_id_seq', 1, false);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (login, senha, pessoa_id) FROM stdin;
\N	\N	25
\.


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY consulta (consulta_id, anotacoes, prontuario, status, animal_id, tipo_consulta_id, medico_id) FROM stdin;
\.


--
-- Name: consulta_consulta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('consulta_consulta_id_seq', 1, false);


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY endereco (pessoa_id, bairro, cep, cidade, complemento, logradouro, numero, uf, endereco_id) FROM stdin;
24	rodolfo	60430440	fortaleza	apto 202	Rua João sorongo	1320	\N	1
25	Rodolfo	60430440	Fortaleza	apto 202	Rua João sorongo	1320	\N	2
\.


--
-- Name: endereco_endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('endereco_endereco_id_seq', 2, true);


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY funcionario (admissao, ctps, demissao, matricula, salario, status, pessoa_id, cargo_id) FROM stdin;
\.


--
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY item_pedido (produto, pedido, quantidade, valoritem) FROM stdin;
\.


--
-- Data for Name: medico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY medico (anoformacao, crmv, especialidade, formacao, horario, turno, pessoa_id) FROM stdin;
\.


--
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pedido (pedido_id, data, status, valorpedido, cliente_id) FROM stdin;
\.


--
-- Name: pedido_pedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pedido_pedido_id_seq', 1, false);


--
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY perfil (perfil_id, authority, nome) FROM stdin;
1	ROLE_ADMIN	ADMIN
2	ROLE_USER	USER
3	ROLE_CLIENTE	CLIENTE
4	ROLE_ATENDENTE	ATENDENTE
5	ROLE_ASSISTENTE	ASSISTENTE
6	ROLE_MEDICO	MEDICO
7	ROLE_GERENTE	GERENTE
\.


--
-- Name: perfil_perfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('perfil_perfil_id_seq', 7, true);


--
-- Data for Name: perfil_permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY perfil_permissao (perfil_id, permissao_id) FROM stdin;
1	1
1	2
1	3
1	4
1	5
1	6
1	7
1	8
1	9
1	10
1	11
1	12
1	13
1	14
1	15
\.


--
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY permissao (permissao_id, nome) FROM stdin;
1	CADASTRAR_USUARIO
2	ALTERAR_USUARIO
3	BLOQUEAR_USUARIO
4	CADASTRAR_CLIENTE
5	ALTERAR_CLIENTE
6	CADASTRAR_ANIMAL
7	ALTERAR_ANIMAL
8	CADASTRAR_FUNCIONARIO
9	ALTERAR_FUNCIONARIO
10	CADASTRAR_PERFIL
11	MENU_CADASTRO
12	MENU_SERVICO
13	MENU_RELATORIO
14	MENU_AGENDA
15	MENU_CAIXA
\.


--
-- Name: permissao_permissao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permissao_permissao_id_seq', 15, true);


--
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pessoa (pessoa_id, celular, cpf, datanascimento, email, nome, sexo, telefone) FROM stdin;
1	85-94028072	71322914320	1975-08-07	fcoedilson@gmail.com	Francisco Edilson do Nascimento	MASC	85-30231261
25	8530231254	71222381220	\N	fcoedilson@gmail.com	José da Silva	MASC	8530231254
\.


--
-- Name: pessoa_pessoa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pessoa_pessoa_id_seq', 25, true);


--
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produto (produto_id, estoque, nome, precocusto, precovenda) FROM stdin;
\.


--
-- Name: produto_produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produto_produto_id_seq', 1, false);


--
-- Data for Name: servico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY servico (servico_id, descricao, nome, valor) FROM stdin;
\.


--
-- Name: servico_servico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('servico_servico_id_seq', 1, false);


--
-- Data for Name: tipoconsulta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipoconsulta (tipo_consulta_id, descricao, preco) FROM stdin;
\.


--
-- Name: tipoconsulta_tipo_consulta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipoconsulta_tipo_consulta_id_seq', 1, false);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (datacadastro, login, senha, status, pessoa_id, perfil_id) FROM stdin;
2014-09-05 00:00:00	edilson	827ccb0eea8a706c4c34a16891f84e7b	ATIVO	1	1
\.


--
-- Name: animal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (aninal_id);


--
-- Name: atendimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY atendimento
    ADD CONSTRAINT atendimento_pkey PRIMARY KEY (atendimeto_id);


--
-- Name: caixa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY caixa
    ADD CONSTRAINT caixa_pkey PRIMARY KEY (caixa_id);


--
-- Name: cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (cargo_id);


--
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (pessoa_id);


--
-- Name: consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (consulta_id);


--
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (pessoa_id);


--
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (pessoa_id);


--
-- Name: item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (pedido);


--
-- Name: medico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (pessoa_id);


--
-- Name: pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (pedido_id);


--
-- Name: perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (perfil_id);


--
-- Name: permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (permissao_id);


--
-- Name: pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (pessoa_id);


--
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (produto_id);


--
-- Name: servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY servico
    ADD CONSTRAINT servico_pkey PRIMARY KEY (servico_id);


--
-- Name: tipoconsulta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipoconsulta
    ADD CONSTRAINT tipoconsulta_pkey PRIMARY KEY (tipo_consulta_id);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (pessoa_id);


--
-- Name: fk3ddd7d4f122d5a8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY caixa
    ADD CONSTRAINT fk3ddd7d4f122d5a8 FOREIGN KEY (funcionario_id) REFERENCES funcionario(pessoa_id);


--
-- Name: fk4654db065989228; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atendimento
    ADD CONSTRAINT fk4654db065989228 FOREIGN KEY (servico_id) REFERENCES servico(servico_id);


--
-- Name: fk4654db06d1301ac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atendimento
    ADD CONSTRAINT fk4654db06d1301ac FOREIGN KEY (animal_id) REFERENCES animal(aninal_id);


--
-- Name: fk4654db0f122d5a8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atendimento
    ADD CONSTRAINT fk4654db0f122d5a8 FOREIGN KEY (funcionario_id) REFERENCES funcionario(pessoa_id);


--
-- Name: fk5b4d8b0e5fdfafac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk5b4d8b0e5fdfafac FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id);


--
-- Name: fk5b4d8b0eab451dcc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk5b4d8b0eab451dcc FOREIGN KEY (pessoa_id) REFERENCES pessoa(pessoa_id);


--
-- Name: fk752a7a1ccb270448; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT fk752a7a1ccb270448 FOREIGN KEY (cliente_id) REFERENCES cliente(pessoa_id);


--
-- Name: fk883f1ed45fdfafac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk883f1ed45fdfafac FOREIGN KEY (perfil_id) REFERENCES perfil(perfil_id);


--
-- Name: fk883f1ed49d7441a8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY perfil_permissao
    ADD CONSTRAINT fk883f1ed49d7441a8 FOREIGN KEY (permissao_id) REFERENCES permissao(permissao_id);


--
-- Name: fk89237969ab451dcc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medico
    ADD CONSTRAINT fk89237969ab451dcc FOREIGN KEY (pessoa_id) REFERENCES pessoa(pessoa_id);


--
-- Name: fk8e420365cb270448; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk8e420365cb270448 FOREIGN KEY (cliente_id) REFERENCES cliente(pessoa_id);


--
-- Name: fk96841ddaab451dcc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk96841ddaab451dcc FOREIGN KEY (pessoa_id) REFERENCES pessoa(pessoa_id);


--
-- Name: fkb3a9c5bb8d907088; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkb3a9c5bb8d907088 FOREIGN KEY (cargo_id) REFERENCES cargo(cargo_id);


--
-- Name: fkb3a9c5bbab451dcc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fkb3a9c5bbab451dcc FOREIGN KEY (pessoa_id) REFERENCES pessoa(pessoa_id);


--
-- Name: fke202e6153434bc55; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT fke202e6153434bc55 FOREIGN KEY (tipo_consulta_id) REFERENCES tipoconsulta(tipo_consulta_id);


--
-- Name: fke202e6156d1301ac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT fke202e6156d1301ac FOREIGN KEY (animal_id) REFERENCES animal(aninal_id);


--
-- Name: fke202e615be21a28c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT fke202e615be21a28c FOREIGN KEY (medico_id) REFERENCES medico(pessoa_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

