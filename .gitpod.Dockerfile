FROM gitpod/workspace-full
USER gitpod

RUN sudo sh -c '(echo "#!/usr/bin/env sh" && curl -L https://github.com/lihaoyi/Ammonite/releases/download/2.0.4/2.13-2.0.4) > /usr/local/bin/amm && chmod +x /usr/local/bin/amm' 

RUN brew install scala sbt scalaenv

RUN scalaenv install scala-2.13.2 && scalaenv global scala-2.13.2

# RUN brew install coursier/formulas/coursier

# RUN sudo env "PATH=$PATH" coursier bootstrap org.scalameta:scalafmt=

