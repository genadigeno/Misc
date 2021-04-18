
  insert into [oauth_client_details] ([client_id]
      ,[resource_ids]
      ,[client_secret]
      ,[scope]
      ,[authorized_grant_types]
      ,[web_server_redirect_uri]
      ,[authorities]
      ,[access_token_validity]
      ,[refresh_token_validity]
      ,[additional_information]
      ,[autoapprove])
	  values ('clientapp', null, '$2a$10$z3l5PdHnzZvxc6vGD1qYxeesk4XVrhWQlzQ3LaaRErjfnYO/vY222',
	  'read_profile, read_contacts', 'authorization_code', 'http://localhost:9000/callback',
	  null, 3000, -1, null, 0)