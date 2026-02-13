from flask import Flask

from src.routes import logs_bp


def create_app() -> Flask:
    """Application factory for creating Flask app instances."""
    app = Flask(__name__)
    app.register_blueprint(logs_bp)
    return app


app = create_app()


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
