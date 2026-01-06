#!/bin/bash

# URL de l'API à tester
API_URL='http://localhost:8085/random?min=0&max=100'

# Nombre total de requêtes à envoyer
TOTAL_REQUESTS=2000

# Nombre de requêtes simultanées
CONCURRENT_REQUESTS=20

# Génération des numéros de requêtes
idx=1
while [ $idx -eq 1 ]
do
	seq 1 $TOTAL_REQUESTS | xargs -P $CONCURRENT_REQUESTS -I {} bash -c "curl -s -o /dev/null -w 'Requête $1: %{http_code}\n' '$API_URL'"
done
