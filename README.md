<!-- ABOUT THE PROJECT -->
## Acne Scan

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Our project is an application to detect the type of acne on the skin. The many types of acne make it difficult for us to determine the right type of acne. This will result in improper handling of acne, so that it does not subside or even gets worse. That way we want to help people identify the type of acne on their skin, so that they can provide the right treatment steps. To identify acne, users only need to take a photo of the part of the skin where there is acne. Then the application will give you the final result in the form of the type of acne and the right treatment steps.


### TEAM B21-CAP0398

### Active Member ID and Name

* M3272943 - Riyan Sthefanus Nainggolan
* M0121213 - Wiratama Abisatya
* M2642462  - Muthia Daniyati 
* C2642461 - Indah Dwi Setyaningrum
* A1831881 - Robertus Luhut Pandapotan Pakpahan
* A1831882 - Fikran Akbar

### Selected Theme : Healthcare

## Getting Started

First, preparing the tflite model for direct inclusion in the android project. Here are the steps:
* Download the dataset [dermenet](www.dermnet.com0); [skin90](https://www.kaggle.com/dinartas/skin90); [skin50](https://www.kaggle.com/dinartas/skin50), the process are on the notebook.
* Create the training and validation batch using the train generator.
* Create the label by using the train generator function.
* Training the model.
* Validating the model.
* Use the converter to convert the keras model into the tflite model.
* Move the tflite file and the label into assets label in android folder.
* Go the FloatMobilenetClassifier and change the path into our previous model and label.

Second, you need to create a project first to be connected to the android studio project on your device. Here are the steps:
* Open [firebase website](www.firebase.google.com), and click get started
* Create a new project in firebase console
* When the firebase project is created, add your android app to firebase by clicking android icon to add the project
* Register the app by fill the name of android package name (mandatory), app name (optional) and debug signing SHA-1 (optional)
* After doing it, you will file file namely "google-services.json". you will replace the google-services file with the one you downloaded
* In the next step you will be asking for add firebase sdk, you can skip it
* After you have finished setting up the firebase project, now you need to prepare some of the services as well such as firebase firestore, cloud storage, and authentication

### Prerequisites

There are several conditions before you can clone this project and connect it with your own firebase project and use the model you created yourself
* [Latest version of android studio](https://developer.android.com/?hl=id)
* [Firebase project](https://console.firebase.google.com/u/0/)
* [Tflite model](https://www.tensorflow.org/lite/guide)

### Installation

1. Clone the repo https://github.com/LuhutPakpahan/Capstone.git
2. Replace the google-services.json file with your own. The file is located in the app folder ('root folder'/app/google-services.json)
3. Don't forget to make sure that your project is now connected to the firebase project
4. To replace the current model with your own, right click on the app folder and click New > Other > Tensorflow Lite Model
5. Enter your tflite model by navigating the model location to the tflite file you have prepared, then click next and finish
6. Go to "UploadDataActivity" and replace the class reference of the existing model by changing the code "Acnescan6" to the class name of your model.
7. And you're good to go.
Tips: You can use ctrl + r, then write "Acnescan6" and fill in the class name of the model you have entered


<!-- USAGE EXAMPLES -->
## Usage

Prototype ---> [Acne Scan](https://www.figma.com/proto/1rpityhZ5BBXvVF19rVUpJ/Acne-Scan?node-id=18%3A7391&scaling=contain&page-id=14%3A2)



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- CONTACT -->
## Contact

Gmail - [B21-CAP0398](https://groups.google.com/a/bangkit.academy/g/b21-cap0398/about?pli=1) - B21-CAP0398@bangkit.academy

