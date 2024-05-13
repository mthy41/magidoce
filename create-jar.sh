# this is a very bad bash script I'm sorry

cd bin/
jar cvfm magidoce.jar manifest.txt com lib/Raylib-J.jar -C assets .
mv magidoce.jar ..
cd ..