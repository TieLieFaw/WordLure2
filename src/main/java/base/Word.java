package base;

import java.util.Objects;

/**
 * The Word class describes a any word. <br>
 * <br>
 * The class contains the following fields:<br>
 * <br>
 * - {@code partOfSpeech} - part of speech which may accepts the following fields:<br>
 * {@code None, Article, Noun, Verb, Adjective, Adverb, Pronoun, Participle, Preposition, Conjunction, Interjection, Particle, Numeral};<br>
 * <br>
 * - {@code value} - meaning of a specific word<br>
 * <br>
 * - {@code transcription} - transcription of a specific word<br>
 * <br>
 * - {@code translate} - translation of a specific word<br>
 * <br>
 * - {@code sound} - object of type Sound which provides a sound file in WAVE(.wav) format<br>
 * <br>
 * The Word class implements the {@code Comparable<Word>} interface. 
 * The overridden method {@code compare()} compares two words in lexicographical order according to their meaning({@code value} field).<br>
 * <br>
 * The class contains only one open constructor:<br>
 * <br>
 * {@code public Word(String partOfSpeech, String value, String transcription, String translate)}:<br>
 * <br>
 * The parameter {@code partOfSpeech} takes a {@code String} object which contains the value of the part of speech.<br>
 * If the value of {@code partOfSpeech} parameter isn't specified or the value of {@code partOfSpeech} parameter isn't one of the valid 
 * values defined in the enum {@code PartOfSpeech}, then the {@code partOfSpeech} field takes the {@code NONE} value defined in the enum {@code PartOfSpeech}.<br>
 * <br>
 * The parameter {@code value} takes a {@code String} object which contains the value of a specific word.<br>
 * <br>
 * The parameter {@code transcription} takes a {@code String} object which contains the transcription of a specific word.<br>
 * <br>
 * The parameter {@code translate} takes a {@code String} object which contains the translation of a specific word.<br>
 * <br>
 * The constructor doesn't include the operation of initializing the {@code sound} field. To initialize the {@code sound} field, you must use the {@code setSound(String soundPath)} method.
 * The method {@code setSound(String soundPath)} accepts a parameter of type {@code String} which indicates the path in the .wav file on disk.
 */
public final class Word implements Comparable<Word>{

	private PartOfSpeech partOfSpeech;
	private String value;
	private String transcription;
	private String translate;
	private Sound sound;

	public Word(String partOfSpeech, String value, String transcription, String translate) {
		if(partOfSpeech == null)
			this.partOfSpeech = PartOfSpeech.None;
		else {
			switch(partOfSpeech) {
			case "Article":
				this.partOfSpeech = PartOfSpeech.Article;
				break;
			case "Noun":
				this.partOfSpeech = PartOfSpeech.Noun;
				break;
			case "Verb":
				this.partOfSpeech = PartOfSpeech.Verb;
				break;
			case "Adjective":
				this.partOfSpeech = PartOfSpeech.Adjective;
				break;
			case "Adverb":
				this.partOfSpeech = PartOfSpeech.Adverb;
				break;
			case "Pronoun":
				this.partOfSpeech = PartOfSpeech.Pronoun;
				break;
			case "Participle":
				this.partOfSpeech = PartOfSpeech.Participle;
				break;
			case "Preposition":
				this.partOfSpeech = PartOfSpeech.Preposition;
				break;
			case "Conjunction":
				this.partOfSpeech = PartOfSpeech.Conjunction;
				break;
			case "Interjection":
				this.partOfSpeech = PartOfSpeech.Interjection;
				break;
			case "Particle":
				this.partOfSpeech = PartOfSpeech.Particle;
				break;
			case "Numeral":
				this.partOfSpeech = PartOfSpeech.Numeral;
				break;
			default:
				this.partOfSpeech = PartOfSpeech.None;
				break;
			}
		}
		this.value = value;
		this.transcription = transcription;
		this.translate = translate;	
	}

	public void setSound(String soundPath){
		sound = new Sound(soundPath);
	}

	@Override
	public int compareTo(Word otherWord) {
		return value.compareTo(otherWord.value);
	}

	public void playSound() {
		sound.play();
	}

	public String getValue() {
		return value;
	}

	public String getTranscription() {
		return transcription;
	}

	public String getTranslate() {
		return translate;
	}

	public String getPartOfSpeech() {
		return partOfSpeech.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(partOfSpeech, value, transcription, translate);
	}

	@Override
	public boolean equals(Object WordObject) {
		if(this == WordObject) return true;
		if(WordObject == null) return false;
		if(getClass() != WordObject.getClass()) return false;
		Word w = (Word) WordObject;
		return partOfSpeech == w.partOfSpeech && value == w.value && transcription == w.transcription && translate == w.translate;
	}

}