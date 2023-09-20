-- S1
CREATE OR REPLACE PROCEDURE public.update_jdbc_customer(
			existing_customer_id bigint,
			new_full_name character varying)
			LANGUAGE 'plpgsql'
			AS $BODY$
			declare
			    dynamic_sql varchar(1000);
			begin
			    dynamic_sql = 'update jdbc_customer set full_name = ''' || new_full_name 
				              || ''' where customer_id = ' || existing_customer_id;
				
			    execute dynamic_sql;
			end
			$BODY$;

-- S2
			