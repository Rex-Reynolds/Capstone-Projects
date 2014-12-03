CREATE TABLE `rar_contacts` (
  `first_name` varchar(60) NOT NULL,
  `middle_name` varchar(60) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `email` varchar(60) NOT NULL,
  `major` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELETE FROM `cb_contactdjp`.`rar_contacts`
WHERE <{where_expression}>;


INSERT INTO `cb_contactdjp`.`rar_contacts`
(`first_name`,
`middle_name`,
`last_name`,
`email`,
`major`)
VALUES
(<{first_name: }>,
<{middle_name: }>,
<{last_name: }>,
<{email: }>,
<{major: }>);

UPDATE `cb_contactdjp`.`rar_contacts`
SET
`first_name` = <{first_name: }>,
`middle_name` = <{middle_name: }>,
`last_name` = <{last_name: }>,
`email` = <{email: }>,
`major` = <{major: }>
WHERE ;

