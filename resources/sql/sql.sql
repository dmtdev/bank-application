select client_id, name , gender, email, phone, city_name from clients, cities
where trim(lower(clients.name)) = ? and cities.city_id=clients.city_id
{1: STRINGDECODE('\u0430\u043d\u043d\u0430')}

-- cyr problems