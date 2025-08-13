pipeline{
	
	agent any

	environment{
		IMAGE_NAME = "my-be-app"
		CONTAINER_NAME = "my-be-container"
	}
	
	stages{
		
		stage ('Cloning the BE repo'){
			steps{
				checkout scm
			}
		}
		
		stage ('build the jar file'){
			steps{
				echo "Creating the jar file from Cloned application ..."
				sh "mvn clean package -Dquarkus.package.type=uber-jar"
				echo "Jar file created , moving into next stage !"
			}
		}

		stage ('docker login'){
			steps{
				echo "Docker login ..."
				withCredentials([usernamePassword(credentialsId:'docker-hub-creds',usernameVariable:'DOCKER_USERNAME',passwordVariable:'DOCKER_PASSWORD')]){
					sh """
						echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
					"""
				}
				echo "Docker login completed successfully !"
			}
		}


		stage ('Docker image creation'){
			steps{
				echo "Building the Docker image from the JAr file ..."
				sh "docker build -t prasanna0218/${IMAGE_NAME}:${BUILD_NUMBER} ."
				echo "Docker image is created !"
			}
		}

		stage ('Docker image push to Hub'){
			steps{
				echo "Docker image pushing into the Docker hub ..."
				sh "docker push prasanna0218/${IMAGE_NAME}:${BUILD_NUMBER}"
				echo "Image is successfully pushed into the docker hub !"
			}
		}

		stage ('Removing the Older Images'){
			steps{
				script{
					def CURRENT_BUILD_NUMBER = env.BUILD_NUMBER.toInteger
					def SECOND_OLDER_BUILD_NUMBER = CURRENT_BUILD_NUMBER-2

					if(SECOND_OLDER_BUILD_NUMBER > 0){
						echo "Older Application Images are found .."
						sh "docker rmi prasanna0218/${IMAGE_NAME}:${SECOND_OLDER_BUILD_NUMBER} || true"
						echo "Older Docker images are removed !"
					}else{
						echo "No Older images found , moving to the next stage !"
					}
				}
			}
		}

		stage ('Stop and remove the old containers'){
			steps{
				script{
					try{
						echo "Stop the running containers ..."
						sh "docker stop ${CONTAINER_NAME} || true"
						sh "docker rm ${CONTAINER_NAME} || true"
						echo "Exixting containers are stopped !"
					}catch(err){
						echo "No Existing containers available , move into next stage !"
					}
				}
			}
		}

		stage ('Executing the docker compose file'){
			steps{ 
				echo "Docker compose file executing stars ..."
				sh "docker compose up"
				echo "Application started successfully !"
			}
		}
	}
	
}