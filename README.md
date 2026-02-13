# API de Logs com Flask

API simples em Flask para registrar eventos em memória, com estrutura organizada para uso em produção simples (application factory, blueprint e armazenamento isolado).

## Requisitos

- Python 3.10+

## Instalação

```bash
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

## Executar

### Desenvolvimento

```bash
python app.py
```

### Produção simples

```bash
gunicorn -w 2 -b 0.0.0.0:5000 "app:app"
```

## Endpoints

### `POST /log`

Cria um evento de log.

**Body JSON:**

```json
{
  "message": "Usuário autenticado"
}
```

**Resposta 201:**

```json
{
  "status": "success",
  "data": {
    "id": 1,
    "message": "Usuário autenticado",
    "timestamp": "2026-01-01T12:00:00.000000+00:00"
  }
}
```

### `GET /logs`

Lista todos os eventos em memória.

**Resposta 200:**

```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "message": "Usuário autenticado",
      "timestamp": "2026-01-01T12:00:00.000000+00:00"
    }
  ],
  "meta": {
    "count": 1
  }
}
```

## Observações

- IDs são incrementais automaticamente.
- Timestamp é gerado automaticamente em UTC (ISO 8601).
- Os dados são mantidos apenas em memória (reiniciar o processo limpa os logs).
