# POST a JSON format entity to the host. 
# The entity is stamped with a date.
curl -sv -X POST --data "{\"content\":\"JSON POST at `date`\"}" --header "Content-Type: application/json" http://localhost:8080/rest/messages/