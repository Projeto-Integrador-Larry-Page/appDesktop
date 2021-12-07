cd C:\Users\igorp\
scp -o StrictHostKeyChecking=yes -i chave.pem eventos.txt ubuntu@ec2-54-164-93-115.compute-1.amazonaws.com:/tmp
del eventos.txt