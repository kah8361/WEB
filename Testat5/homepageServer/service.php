<?php

/**
 * auf true setzen, um DEBUG-Info in das PHP-ErrorLog zu schreiben
 */
$debugToErrorLog = true;

function __autoload($class_name) {
    include $class_name . '.php';
}

/*
 * Services
 */

/**
 * GET person: liefert eine Personenbeschreibung
 */
function getPerson() {
    $persistence = Persistenz::getinstance();
    $person = $persistence->ladePerson();
    echo $person;
}

/**
 * PUT person <JSON-Personenbeschreibung>: speichert eine Personenbeschreibung
 */
function putPerson($person) {
    $persistence = Persistenz::getInstance();
    $persistence->speicherePerson($person);
}

/**
 * POST login <Benutzername/Passwort>
 * Prüft anhand der in der Datei benutzer.txt abgelegten Daten, ob Benutzername und Passwort korrekt sind.
 * Setzt einen entsprechenden Statuscode, 200: OK, 404: Fehler
 */
function postLogin($data) {
    $persistence = Persistenz::getInstance();
    $benutzer = $persistence->ladeBenutzer();
    $datanojson = json_decode($data);
    if($datanojson->passwort === $benutzer['passwort'] && $datanojson->benutzername === $benutzer['benutzername']){
        http_response_code(200);
    }else{
        http_response_code(404);
    }
}

/*
 * Service Dispatcher
 */

$url = $_REQUEST['_url'];
$requestType = $_SERVER['REQUEST_METHOD'];
$body = file_get_contents('php://input');
$jsonData = json_decode($body);

if ($GLOBALS["debugToErrorLog"]) {
    error_log("REST-Call: ".$requestType.' '.$url.':'.$body);
}

if($url === '/login'){
    if($requestType === 'POST'){
        postLogin($body);
    }else{
        badRequest($requestType, $url, $body);
    }
}elseif ($url === '/person') {
    if($requestType === 'GET'){
        getPerson();
    }else if($requestType === 'PUT'){
        putPerson($body);
    }
} else {
    badRequest($requestType, $url, $body);
}

function badRequest($requestType, $url, $body) {
 	http_response_code(400);
    if ($GLOBALS["debugToErrorLog"]) {
        error_log("bad request");
    }
    die('Ungültiger Request: '.$requestType.' '.$url.' '.$body);
}

?>