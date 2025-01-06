# Tabla CRUD API

Este repositorio es la API para el proyecto de [Tabla CRUD](https://github.com/perrypicker/table-crud).

## Tecnologías utilizadas

Este proyecto es una REST API hecha con Spring Boot.

## ¿Cómo funciona?

La aplicación se conecta a una base de datos MySQL, mapea una entidad `Item` con JPA Repository y pone en funcionamiento los endpoints correspondientes.

## URL base

`http://localhost:8080/api`

## Endpoints

Estos son los 5 endpoints dispoinibles:

**GET** `/items`
Obtiene todos los productos.

**Ejemplo:** `http://localhost:8080/api/items`

**Respuesta:**
Status: 200 OK
Body:
```json
[
  {
    "id": 1,
    "detail": "Manzanas Golden",
    "price": 45.5,
    "stock": 120
  },
  {
    "id": 2,
    "detail": "Leche Entera 1L",
    "price": 19.9,
    "stock": 250
  },
  ...
]
```

**GET** `/items/{id}`
Obtiene el producto con el `id` especificado.

**Ejemplo:** `http://localhost:8080/api/items/7`

**Respuesta:**
Status: 200 OK
Body:
```json
{
  "id": 7,
  "detail": "Arroz Blanco 1kg",
  "price": 23.5,
  "stock": 200
}
```

**POST** `/items`
Crea un nuevo producto.

**Ejemplo:** `http://localhost:8080/api/items`

**Body:**
```json
{
  "detail": "Caja de lápices",
  "price": 24.5,
  "stock": 40
}
```
El `id` es generado por el motor de base de datos.

**Respuesta:**
Status: 201 CREATED
Body:
```json
{
  "id": {{newId}},
  "detail": "Caja de lápices",
  "price": 24.5,
  "stock": 40
}
```

**PUT** `/items/{id}`

Actualiza un producto por `id`.

**Ejemplo:** `http://localhost:8080/api/items/11`

**Body:**
```json
{
  "detail": "Caja de lápices",
  "price": 27,
  "stock": 32
}
```

**Respuesta:**
Status: 200 OK
Body:
```json
{
  "id": {{newId}},
  "detail": "Caja de lápices",
  "price": 27,
  "stock": 32
}
```

**DELETE** `/items/{id}`

Elimina un producto por `id`.

**Ejemplo:** `http://localhost:8080/api/items/11`

**Respuesta:**
Status: 200 OK
Body:
```json
{
  "id": 11,
  "detail": "Caja de lápices",
  "price": 27,
  "stock": 32
}
```
