COPY
(SELECT muzeji.naziv, drzava, grad, godina_osnivanja, muzeji.tip, velicina_kolekcije, posjetitelji, izlozbeni_prostor, web_stranica, online_setnja, eksponati.naziv, eksponati.tip
FROM muzeji JOIN eksponati ON muzeji.id = eksponati.id_muzej
ORDER BY muzeji.id, eksponati.id)
TO 'C:\fer predmeti\otvrac\lab1\muzeji.csv' WITH (FORMAT CSV, HEADER);

COPY (
	SELECT jsonb_agg(muzeji)
	FROM (
		SELECT m.naziv, drzava, grad, godina_osnivanja, m.tip, velicina_kolekcije, posjetitelji, izlozbeni_prostor, web_stranica, online_setnja,
		jsonb_agg(json_build_object('naziv', e.naziv, 'tip', e.tip)) AS eksponati
		FROM muzeji m
		JOIN eksponati e ON m.id = e.id_muzej
		GROUP BY m.id
	) AS muzeji
) TO 'C:\fer predmeti\otvrac\lab1\muzeji.json'