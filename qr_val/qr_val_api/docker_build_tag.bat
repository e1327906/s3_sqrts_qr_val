# Log in to Docker Hub with Personal Access Token
docker login -u e1327906 -p dckr_pat_79VQB7NQGNm9q2OGJHQpSOjhKdU

# Build the Docker image
docker build -t e1327906/qr_val:latest .

# (Optional) Tag the Docker image with a specific version
docker tag e1327906/qr_gen:latest e1327906/qr_val:v5.0

# Push the Docker image to Docker Hub
docker push e1327906/qr_val:latest
docker push e1327906/qr_val:v5.0
