-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 22/06/2023 às 03:24
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `appdb`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `categoria`
--

INSERT INTO `categoria` (`id`, `nome`) VALUES
(2, 'venda'),
(4, 'salario'),
(5, 'automovel'),
(6, 'educacao'),
(7, 'moradia'),
(8, 'alimentacao');

-- --------------------------------------------------------

--
-- Estrutura para tabela `despesa`
--

CREATE TABLE `despesa` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `valor_mensal` double NOT NULL,
  `valor_ocasional` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `despesa`
--

INSERT INTO `despesa` (`id`, `id_categoria`, `descricao`, `mes`, `ano`, `valor_mensal`, `valor_ocasional`) VALUES
(4, 5, 'combustivel', 6, 2023, 800, 0),
(5, 5, 'ipva', 6, 2023, 0, 3500),
(6, 5, 'seguro', 6, 2023, 0, 4500),
(7, 6, 'mensalidade escolar', 6, 2023, 2500, 0),
(8, 6, 'material escolar', 6, 2023, 0, 1000),
(9, 7, 'aluguel', 6, 2023, 2500, 0),
(10, 8, 'mercado', 6, 2023, 1040, 0),
(13, 6, 'caiu a escola', 6, 2022, 0, 10000);

-- --------------------------------------------------------

--
-- Estrutura para tabela `fundo`
--

CREATE TABLE `fundo` (
  `id` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `valor_mensal` double NOT NULL,
  `valor_ocasional` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `fundo`
--

INSERT INTO `fundo` (`id`, `descricao`, `mes`, `ano`, `valor_mensal`, `valor_ocasional`) VALUES
(2, 'ipva', 6, 2023, 300, 0),
(3, 'seguro', 6, 2023, 375, 0),
(4, 'material escolar', 6, 2023, 85, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `investimento`
--

CREATE TABLE `investimento` (
  `id` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `valor_mensal` double NOT NULL,
  `valor_ocasional` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `investimento`
--

INSERT INTO `investimento` (`id`, `descricao`, `mes`, `ano`, `valor_mensal`, `valor_ocasional`) VALUES
(4, 'poupanca', 6, 2023, 0, 7000),
(5, 'poupanca', 6, 2023, 2200, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `rendimento`
--

CREATE TABLE `rendimento` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `valor_mensal` double NOT NULL,
  `valor_ocasional` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Despejando dados para a tabela `rendimento`
--

INSERT INTO `rendimento` (`id`, `id_categoria`, `descricao`, `mes`, `ano`, `valor_mensal`, `valor_ocasional`) VALUES
(13, 4, 'Salario Mensal', 6, 2023, 10000, 0),
(14, 4, '13 salario', 12, 2023, 0, 8000),
(15, 4, 'Ferias', 7, 2023, 0, 8000),
(16, 2, 'Notebook', 6, 2023, 0, 5000);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `despesa`
--
ALTER TABLE `despesa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_categoria` (`id_categoria`) USING BTREE;

--
-- Índices de tabela `fundo`
--
ALTER TABLE `fundo`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `investimento`
--
ALTER TABLE `investimento`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `rendimento`
--
ALTER TABLE `rendimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_categoria` (`id_categoria`) USING BTREE;

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `despesa`
--
ALTER TABLE `despesa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `fundo`
--
ALTER TABLE `fundo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `investimento`
--
ALTER TABLE `investimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `rendimento`
--
ALTER TABLE `rendimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
