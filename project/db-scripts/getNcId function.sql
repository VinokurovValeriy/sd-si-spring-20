delimiter &&
create function getNcId()
returns int
begin
declare result int;
update nc_id_keeper set id = id + 1;
select id into result from nc_id_keeper;
return result;
end
&&
delimiter ;