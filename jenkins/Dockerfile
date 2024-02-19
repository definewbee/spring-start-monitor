FROM jenkins/inbound-agent:3107.v665000b_51092-15

USER root

RUN apt-get update \
    && apt-get install -y curl \
    && curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod 755 kubectl \
    && mv kubectl /bin

USER root