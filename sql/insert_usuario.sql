-- senha 123
insert into public.pessoa
(
   nome,
   sobre_nome,
   dt_nascimento,
   sexo,
   ativo,
   login,
   senha,
   perfil,
   dt_cadastro
)
values
(
   'Daniel',
   'Costa',
   '1983-08-20',
   'M',
   true,
   'dan',
   '$2a$12$xx0CFMCeHLfjKe12ewiqLuMwi/3VkAIp9N9KO0OjfGwTnuskNL46a',
   'admin',
   now ()
)