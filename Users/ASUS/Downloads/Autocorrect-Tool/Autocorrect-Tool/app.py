import streamlit as st
from textblob import TextBlob
from deep_translator import GoogleTranslator

st.set_page_config(
    page_title="Multi Language Autocorrect Tool",
    page_icon="🌍",
    layout="centered"
)

st.title("🌍Multi-Language Autocorrect Tool")

st.markdown("Supports English and many world languages.")

# Language options
languages = {
    "English": "en",
    "Hindi": "hi",
    "French": "fr",
    "Spanish": "es",
    "German": "de"
}

selected_language = st.selectbox(
    "Select Language",
    list(languages.keys())
)

text = st.text_area(
    "Enter Text",
    height=200,
    placeholder="Type here..."
)

if st.button("Correct Text"):

    if text.strip() == "":
        st.warning("Please enter some text.")

    else:

        # English autocorrect
        if selected_language == "English":

            corrected = str(TextBlob(text).correct())

            st.subheader("Corrected Text")
            st.success(corrected)

        else:

            # Translate to English
            translated = GoogleTranslator(
                source='auto',
                target='en'
            ).translate(text)

            # Correct English
            corrected_english = str(TextBlob(translated).correct())

            # Translate back
            final_output = GoogleTranslator(
                source='en',
                target=languages[selected_language]
            ).translate(corrected_english)

            st.subheader("Corrected Text")
            st.success(final_output)