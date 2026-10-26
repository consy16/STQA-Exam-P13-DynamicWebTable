
FROM mcr.microsoft.com/devcontainers/java:1-21-bookworm

USER root

RUN rm -f /etc/apt/sources.list.d/yarn.list \
    && rm -f /etc/apt/sources.list.d/yarn.sources \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
       maven \
       chromium \
       chromium-driver \
    && rm -rf /var/lib/apt/lists/*

USER vscode
