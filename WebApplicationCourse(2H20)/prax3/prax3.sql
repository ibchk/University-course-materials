-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2020 at 11:18 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prax3`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(50) NOT NULL,
  `user_id` int(50) NOT NULL,
  `post_id` int(50) NOT NULL,
  `content` varchar(3000) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `user_id`, `post_id`, `content`, `updated_at`, `created_at`) VALUES
(26, 136, 20, 'Fine', '2020-11-18 13:53:11', '2020-11-18 13:53:11'),
(27, 136, 23, 'Still cold...', '2020-11-18 13:54:25', '2020-11-18 13:54:25');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `id` int(50) NOT NULL,
  `user1_id` int(50) NOT NULL,
  `user2_id` int(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`id`, `user1_id`, `user2_id`, `created_at`, `updated_at`) VALUES
(11, 139, 137, '2020-11-18 01:06:05', '2020-11-18 01:06:05'),
(13, 140, 137, '2020-11-18 01:09:33', '2020-11-18 01:09:33'),
(18, 136, 139, '2020-11-18 01:50:10', '2020-11-18 01:50:10');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(50) NOT NULL,
  `creator_name` varchar(50) NOT NULL,
  `user_id` int(50) NOT NULL,
  `content` varchar(3000) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `creator_name`, `user_id`, `content`, `created_at`, `updated_at`) VALUES
(19, '', 139, 'What are you thinking about BLM, guys?', '2020-11-18 01:05:37', '2020-11-18 01:05:37'),
(20, '', 139, 'Hey, how are you?', '2020-11-18 01:05:57', '2020-11-18 01:05:57'),
(21, '', 140, 'Russia and Estonia can recreate communism!', '2020-11-18 01:09:12', '2020-11-18 01:09:12'),
(22, '', 139, 'Am i joke to you?', '2020-11-18 01:56:04', '2020-11-18 01:56:04'),
(23, '', 136, 'Estonia is cold like teachers hurts...', '2020-11-18 02:10:15', '2020-11-18 02:10:15');

-- --------------------------------------------------------

--
-- Table structure for table `reactions`
--

CREATE TABLE `reactions` (
  `id` int(50) NOT NULL,
  `user_id` int(50) NOT NULL,
  `post_id` int(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reactions`
--

INSERT INTO `reactions` (`id`, `user_id`, `post_id`, `created_at`, `updated_at`) VALUES
(11, 136, 23, '2020-11-18 14:40:52', '2020-11-18 14:40:52'),
(14, 136, 22, '2020-11-18 19:09:57', '2020-11-18 19:09:57'),
(15, 136, 20, '2020-11-18 19:10:06', '2020-11-18 19:10:06'),
(17, 136, 24, '2020-11-18 19:16:09', '2020-11-18 19:16:09'),
(20, 136, 25, '2020-11-18 19:28:09', '2020-11-18 19:28:09'),
(22, 136, 27, '2020-11-18 19:28:52', '2020-11-18 19:28:52'),
(24, 136, 28, '2020-11-18 20:42:53', '2020-11-18 20:42:53');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `city`, `email`, `password`, `description`, `created_at`, `updated_at`, `country`) VALUES
(136, 'Ilja', '', 'Tallinn', 'ilja199@gmail.com', '$2a$09$anexamplestringforsalep4boJM854O2vEJKXM4cCADlT1kq9g1K', 'This is my page', '2020-11-15 19:58:47', '2020-11-18 21:03:06', 'Estonia'),
(137, 'Artur', '', 'Narva', 'narva@narva.com', '$2a$09$anexamplestringforsaleMI5EL6t8UYdWbpxHSnNe14zgGNVKbEy', 'Some text', '2020-11-15 20:08:16', '2020-11-18 21:04:24', 'Estonia'),
(139, 'Nikita', '', 'LA', 'blm@blm.com', '$2a$09$anexamplestringforsaleMI5EL6t8UYdWbpxHSnNe14zgGNVKbEy', 'BLM is my life!!!', '2020-11-18 01:05:14', '2020-11-18 21:04:48', 'USA'),
(140, 'Vassily', '', 'Berlin', 'russia@russia.ru', '$2a$09$anexamplestringforsaleMI5EL6t8UYdWbpxHSnNe14zgGNVKbEy', 'My grandad was a great man, liked his views!', '2020-11-18 01:08:45', '2020-11-18 21:04:56', 'Germany');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user1_id` (`user1_id`),
  ADD KEY `user2_id` (`user2_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `reactions`
--
ALTER TABLE `reactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `friends`
--
ALTER TABLE `friends`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `reactions`
--
ALTER TABLE `reactions`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=153;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
