delimiter //
drop trigger if exists noDoc//

create trigger noDoc
before insert on transaccion
for each row
begin
	declare cantArt int default 0;
    select count(Id) + 1 into cantArt from transaccion where new.documento_Id = documento_Id and new.cuenta_No=cuenta_No and new.Tipo=Tipo;
    set new.No_doc=cantArt;    
end;//
delimiter ;